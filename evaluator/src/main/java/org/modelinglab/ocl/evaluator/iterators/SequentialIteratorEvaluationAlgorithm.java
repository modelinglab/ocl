/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators;

import java.util.List;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.vartables.VariableAlreadyExistException;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.walker.ExpressionWalker;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequentialIteratorEvaluationAlgorithm extends IteratorEvaluationAlgorithm {

    @Nonnull
    @Override
    public <E> OclValue<?> evaluateSingleItVar(final CollectionType sourceType, final List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, IteratorEvaluator<E> concreteEvaluator, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException {
        assert itExp.getBody().getType().conformsTo(concreteEvaluator.expectedBodyClassifier());
        assert itExp.getIterators().size() == 1;
        assert !runtimeEnv.getVarTable().exists(itExp.getIterators().get(0).getName()) :
                "Iterator variable should not be declared in variable table";

        final OclValueIndirection<E> accumulator = concreteEvaluator.createAccumulator(sourceType, sourceAsList, itExp, runtimeEnv);

        int index = 0;

        OclValue<?> result;

        try {
            for (final OclValue<?> element : sourceAsList) {
                OclValue<?> bodyVal = evaluateBody(element, null, itExp, runtimeEnv);

                OclValue<?> taskResult = concreteEvaluator.createTaskResult(bodyVal, runtimeEnv, itExp, element, null, index);
                
                result = concreteEvaluator.acumResult(accumulator, taskResult, index, sourceAsList);

                if (result != null) {
                    return result;
                }

                index++;
            }
        } catch (VariableAlreadyExistException ex) {
            throw new OclEvaluationException(itExp, ex);
        }

        result = concreteEvaluator.extractResult(accumulator, sourceType, sourceAsList, itExp, runtimeEnv);
        assert result != null;
        return result;
    }

    @Nonnull
    @Override
    public <E> OclValue<?> evaluateDoubleItVar(final CollectionType sourceType, final List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, IteratorEvaluator<E> concreteEvaluator, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException {

        assert itExp.getBody().getType().conformsTo(concreteEvaluator.expectedBodyClassifier());
        assert itExp.getIterators().size() == 2;
        assert !runtimeEnv.getVarTable().exists(itExp.getIterators().get(0).getName()) :
                "Iterator variable should not be declared in variable table";
        assert !runtimeEnv.getVarTable().exists(itExp.getIterators().get(1).getName()) :
                "Iterator variable should not be declared in variable table";

        final OclValueIndirection<E> accumulator = concreteEvaluator.createAccumulator(sourceType, sourceAsList, itExp, runtimeEnv);

        OclValue<?> result;

        int index = 0;

        try {
            for (int i = 0; i < sourceAsList.size(); i++) {
                OclValue<?> element1 = sourceAsList.get(i);

                for (int j = 0; j < sourceAsList.size(); j++) {
                    OclValue<?> element2 = sourceAsList.get(j);
                    OclValue<?> bodyVal = evaluateBody(element1, element2, itExp, runtimeEnv);

                    OclValue<?> taskResult = concreteEvaluator.createTaskResult(bodyVal, runtimeEnv, itExp, element1, element2, index);

                    result = concreteEvaluator.acumResult(accumulator, taskResult, index, sourceAsList);

                    if (result != null) {
                        return result;
                    }

                    index++;
                }

            }
        } catch (VariableAlreadyExistException ex) {
            throw new OclEvaluationException(itExp, ex);
        }

        result = concreteEvaluator.extractResult(accumulator, sourceType, sourceAsList, itExp, runtimeEnv);
        assert result != null;
        return result;
    }

    private OclValue<?> evaluateBody(OclValue<?> element1, OclValue<?> element2, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) throws VariableAlreadyExistException {
        EvaluatorVisitorArg newRuntimeEnv = runtimeEnv.createSubEnvironment();

        Variable itVar = itExp.getIterators().get(0);
        newRuntimeEnv.getVarTable().createVariable(itVar, element1);

        if (element2 != null) {
            itVar = itExp.getIterators().get(1);
            newRuntimeEnv.getVarTable().createVariable(itVar, element2);
        }

        return itExp.getBody().accept(ExpressionWalker.getInstance(), newRuntimeEnv);
    }
    
    public static class SequentialIteratorEvaluationAlgorithmProvider implements IteratorEvaluationAlgorithmProvider {

        @Override
        public <E> IteratorEvaluationAlgorithm getAlgorithm(IteratorExp exp, IteratorEvaluator<E> evaluator, EvaluatorVisitorArg rutimeEnv) {
            return new SequentialIteratorEvaluationAlgorithm();
        }
        
    }
}
