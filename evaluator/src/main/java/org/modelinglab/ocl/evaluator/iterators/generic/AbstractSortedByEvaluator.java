/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.modelinglab.ocl.core.ast.Namespace;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.expressions.VariableExp;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitorAdaptor;
import org.modelinglab.ocl.core.exceptions.AmbiguosOperationCall;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.vartables.VariableAlreadyExistException;
import org.modelinglab.ocl.core.vartables.VariableNotExistException;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractSortedByEvaluator.Pair;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.walker.IllegalOperationException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public abstract class AbstractSortedByEvaluator implements IteratorEvaluator<ArrayList<Pair>> {

    public abstract OclValue<?> createReturnValue(CollectionType sourceType, List<OclValue<?>> sortedList);

    @Override
    public Classifier expectedBodyClassifier() {
        /*
         * body type has to be orderable, but that condition is no expressable statically
         */
        return AnyType.getInstance();
    }

    @Override
    public OclValueIndirection<ArrayList<Pair>> createAccumulator(CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        return new OclValueIndirection<>(new ArrayList<Pair>(sourceAsList.size()));
    }

    @Override
    public OclValue<?> acumResult(OclValueIndirection<ArrayList<Pair>> accumulator, OclValue<?> taskResult, int index, List<? extends OclValue<?>> sourceAsList) {
        if (taskResult.getType().oclIsInvalid()) {
            return InvalidValue.instantiate();
        }
        if (taskResult.getType().oclIsUndefined()) {
            /*
             * If we add the null value to the accumulator some comparation are going to return an invalid value,
             * so it would be impossible to sort the result and we should return invalid... so we can return
             * invalid now :P
             */
            return InvalidValue.instantiate();
        }
        Pair p = new Pair(sourceAsList.get(index), taskResult);

        accumulator.getValue().add(p);

        return null;
    }

    @Override
    public OclValue<?> createTaskResult(OclValue<?> bodyValue, EvaluatorVisitorArg originalRuntimeEnv, IteratorExp itExp, OclValue<?> element1, OclValue<?> element2, int index) {
        return bodyValue;
    }

    @Override
    public OclValue<?> extractResult(OclValueIndirection<ArrayList<Pair>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        Comparator<Pair> comparator = itExp.getBody().getType().accept(new SorterFactory(), runtimeEnv);
        assert comparator != null : "There is no comparator that compares objects of type '" + itExp.getBody().getType()+ "'"; 
        Collections.sort(accumulator.getValue(), comparator);

        ArrayList<OclValue<?>> resultList = new ArrayList<>(accumulator.getValue().size());
        for (final Pair pair : accumulator.getValue()) {
            resultList.add(pair.sourceVal);
        }
        
        return createReturnValue(sourceType, resultList);
    }

    public static class Pair {

        private OclValue<?> sourceVal;
        private OclValue<?> bodyVal;

        public Pair(OclValue<?> sourceVal, OclValue<?> bodyVal) {
            this.sourceVal = sourceVal;
            this.bodyVal = bodyVal;
        }
    }

    public static class SorterFactory extends ClassifierVisitorAdaptor<Comparator<Pair>, EvaluatorVisitorArg> {

        @Override
        public Comparator<Pair> visit(PrimitiveType primitiveType, EvaluatorVisitorArg argument) {
            switch (primitiveType.getPrimitiveKind()) {
                case UNLIMITED_NATURAL:
                case INTEGER:
                case REAL:
                    return new NumberSorter();
                case STRING:
                    return new StringSorter();
                default:
                    return visit((Classifier) primitiveType, argument);
            }
        }

        @Override
        public Comparator<Pair> visit(Classifier classifier, EvaluatorVisitorArg argument) {
            return new UserOperationSorter(classifier, argument);
        }
    }

    /**
     * This comparator delegates the order decision in the "Less" operatation defined by the user.
     */
    public static class UserOperationSorter implements Comparator<Pair> {

        private Variable var1;
        private Variable var2;
        private OperationCallExp comparatorExp;
        private EvaluatorVisitorArg eva;
        private OperationEvaluator opEval;
        private OperationEvaluator.SwitchArgument opSwitchArg;

        public UserOperationSorter(Classifier commonType, EvaluatorVisitorArg _eva) {
            this.eva = _eva.createSubEnvironment();

            var1 = new Variable("_comparator_var1", null, commonType);
            var2 = new Variable("_comparator_var2", null, commonType);
            VariableExp var1Exp = new VariableExp(var1);
            VariableExp var2Exp = new VariableExp(var2);

            try {
                eva.getVarTable().createVariable(var1, InvalidValue.instantiate());
                eva.getVarTable().createVariable(var2, InvalidValue.instantiate());
            } catch (VariableAlreadyExistException ex) {
                throw new AssertionError("Comparator variables should be unique.");
            }

            Operation lessOp = getLessOperation(commonType, eva);
            comparatorExp = new OperationCallExp();
            comparatorExp.setReferredOperation(lessOp);
            comparatorExp.setSource(var1Exp);
            comparatorExp.addArgument(var2Exp);

            opEval = eva.getUserEvalEnv().getUserOperationEvaluator(lessOp);
            
            if (opEval == null) {
                throw new IllegalStateException("There is no evaluator that evaluates "+lessOp);
            }
            

            ArrayList<OclValue<?>> argList = new ArrayList<>(1);
            argList.add(InvalidValue.instantiate());

            opSwitchArg = new OperationEvaluator.SwitchArgument(comparatorExp, argList, eva);
        }

        @Override
        public int compare(Pair o1, Pair o2) {
            if (isLesser(o1.bodyVal, o2.bodyVal)) {
                return -1;
            }
            if (isLesser(o2.bodyVal, o1.bodyVal)) {
                return 1;
            }
            return 0;
        }

        private boolean isLesser(OclValue<?> first, OclValue<?> last) {
            try {
                eva.getVarTable().setValue(var1.getName(), first);
                eva.getVarTable().setValue(var2.getName(), last);
            } catch (VariableNotExistException ex) {
                throw new AssertionError("This should not happend.");
            }

            opSwitchArg.arguments.set(0, last);

            OclValue<?> isLessser = first.accept(opEval, opSwitchArg);

            if (isLessser.getValue() instanceof Boolean) {
                return (Boolean) isLessser.getValue();
            }
            throw new OclEvaluationException(
                    comparatorExp,
                    "It was expected that " + first + " < " + last + " returns a boolean value!");
        }

        private Operation getLessOperation(Classifier c, EvaluatorVisitorArg eva) {
            Namespace ns = new Namespace();
            ns.addMember(c);
            StaticEnvironment env = new StaticEnvironment(eva.getUserEvalEnv().getUserOperationStore(), ns);

            Operation result;
            try {
                result = env.lookupOperation(c, "<", Collections.singletonList(c));
            } catch (AmbiguosOperationCall ex) {
                result = new Operation(null) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
                        return this;
                    }
                };
                result.setSource(c);
                result.addOwnedParameter(new Parameter("other", c, result));
                result.setName("<");

                throw new IllegalOperationException(result, ex);
            }
            if (result == null) {
                throw new IllegalOperationException(result, "Classifier " + c + " is not comparable (it does "
                        + "no own any property like " + result + ") so can not be sorted!");
            }

            return result;

        }
    }

    public static class StringSorter implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            String str1 = (String) o1.bodyVal.getValue();
            String str2 = (String) o2.bodyVal.getValue();

            return str1.compareTo(str2);
        }
    }

    public static class NumberSorter implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            Number n1 = (Number) o1.bodyVal.getValue();
            Number n2 = (Number) o2.bodyVal.getValue();

            Double d1 = n1.doubleValue();
            Double d2 = n2.doubleValue();
            return d1.compareTo(d2);
        }
    }
}
