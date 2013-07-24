/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.evaluators;

import javax.annotation.concurrent.Immutable;
import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.ast.expressions.AssociationEndCallExp;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.core.values.utils.ValueVisitor;
import org.modelinglab.ocl.core.values.utils.ValueVisitorAdapter;
import org.modelinglab.ocl.evaluator.walker.ExpressionWalker;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class AssociationEndCallExpEval {

    private AssociationEndCallExpEval() {
    }

    public static AssociationEndCallExpEval getInstance() {
        return AssociationEndCallExpEvalHolder.INSTANCE;
    }
    
    public OclValue<?> evaluate(AssociationEndCallExp exp, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException {
        OclValue<?> source = exp.getSource().accept(ExpressionWalker.getInstance(), runtimeEnv);

        /*
         * Check if source is correct
         */
        if (!source.getType().conformsTo(exp.getReferredAssociationEnd().getUmlClass())) {
            throw new OclEvaluationException(exp, "It was expected that " + exp.getSource() + " was "
                    + "evaluated to a " + exp.getReferredAssociationEnd().getUmlClass() + ".");
        }
        ValueVisitor<OclValue<?>, SwitchArgument<AssociationEndCallExp>> switcher = SourceSwitcher.instance;
        SwitchArgument<AssociationEndCallExp> arg = new SwitchArgument<>(exp, runtimeEnv);
        
        OclValue<?> result = source.accept(switcher, arg);
        
        runtimeEnv.getUserEvalEnv().getAnalyzer().analyze(exp, source, result);
        
        return result;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return AssociationEndCallExpEval.getInstance();
    }

    private static class SourceSwitcher extends ValueVisitorAdapter<OclValue<?>, SwitchArgument<AssociationEndCallExp>> {

        public final static SourceSwitcher instance = new SourceSwitcher();
        
        @Override
        protected OclValue<?> defaultCase(OclValue<?> val, SwitchArgument<AssociationEndCallExp> arg) {
            throw new AssertionError("It was expected that source was evaluated to an object, null or "
                    + "invalid, not "+val.getType()+"!");
        }

        @Override
        public OclValue<?> visit(ObjectValue<?> val, SwitchArgument<AssociationEndCallExp> arg) {
            try {
                return val.getValue().getProperty(arg.expression.getReferredAssociationEnd().getName());
            } catch (IllegalArgumentException | UmlObject.NotInitializedProperty ex) {
                throw new OclEvaluationException(arg.expression, ex);
            }
        }

        @Override
        public OclValue<?> visit(InvalidValue val, SwitchArgument<AssociationEndCallExp> arg) {
            return val;
        }

        @Override
        public OclValue<?> visit(VoidValue val, SwitchArgument<AssociationEndCallExp> arg) {
            return InvalidValue.instantiate();
        }
        
    }

    private static class AssociationEndCallExpEvalHolder {
        private static final AssociationEndCallExpEval INSTANCE = new AssociationEndCallExpEval();

        private AssociationEndCallExpEvalHolder() {
        }
    }
 }
