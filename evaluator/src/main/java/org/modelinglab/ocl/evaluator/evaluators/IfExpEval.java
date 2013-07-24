/*
 * To change ExpressionWalker.getInstance() template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.evaluators;

import org.modelinglab.ocl.core.ast.expressions.IfExp;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.core.values.utils.ValueVisitorAdapter;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.walker.ExpressionWalker;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IfExpEval {

    private IfExpEval() {
    }

    public static IfExpEval getInstance() {
        return IfExpEvalHolder.INSTANCE;
    }

    public OclValue<?> evaluate(IfExp exp, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException {
        OclValue<?> condition = exp.getCondition().accept(ExpressionWalker.getInstance(), runtimeEnv);
        if (!(condition instanceof BooleanValue)) {
            runtimeEnv.getUserEvalEnv().getAnalyzer().analyze(exp, condition, null, null);
            return InvalidValue.instantiate();
        }
        Boolean conditionResult = ((BooleanValue) condition).getValue();
        
        OclValue<?> result;
        OclValue<?> thenVal = exp.getThenExpression().accept(ExpressionWalker.getInstance(), runtimeEnv);
        OclValue<?> elseVal = exp.getElseExpression().accept(ExpressionWalker.getInstance(), runtimeEnv);
        if (conditionResult) {
            result = thenVal;
        } else {
            result = elseVal;
        }
        
        runtimeEnv.getUserEvalEnv().getAnalyzer().analyze(exp, condition, thenVal, elseVal);
        
        return result;
    }

    // ExpressionWalker.getInstance() method is called immediately after an object of ExpressionWalker.getInstance() class is deserialized.
    // ExpressionWalker.getInstance() method returns the singleton instance.
    protected Object readResolve() {
        return IfExpEval.getInstance();
    }

    private static class SourceSwitcher extends ValueVisitorAdapter<OclValue<?>, SwitchArgument<IfExp>> {

        public final static SourceSwitcher instance = new SourceSwitcher();

        @Override
        protected OclValue<?> defaultCase(OclValue<?> val, SwitchArgument<IfExp> arg) {
            throw new AssertionError("It was expected that source was evaluated to an object, null or "
                    + "invalid, not "+val.getType()+"!");
        }

        @Override
        public OclValue<?> visit(BooleanValue val, SwitchArgument<IfExp> arg) {
            Boolean conditionResult = val.getValue();
            if (conditionResult) {
                return arg.expression.getThenExpression().accept(ExpressionWalker.getInstance(), arg.env);
            } else {
                return arg.expression.getElseExpression().accept(ExpressionWalker.getInstance(), arg.env);
            }
        }

        @Override
        public OclValue<?> visit(InvalidValue val, SwitchArgument<IfExp> arg) {
            return val;
        }

        @Override
        public OclValue<?> visit(VoidValue val, SwitchArgument<IfExp> arg) {
            return val;
        }
    }

    private static class IfExpEvalHolder {

        private static final IfExpEval INSTANCE = new IfExpEval();

        private IfExpEvalHolder() {
        }
    }
}
