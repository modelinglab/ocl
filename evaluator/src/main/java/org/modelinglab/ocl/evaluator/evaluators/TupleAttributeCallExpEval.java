/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.evaluators;

import org.modelinglab.ocl.core.ast.expressions.TupleAttributeCallExp;
import org.modelinglab.ocl.core.ast.types.TupleType;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.TupleValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.core.values.utils.ValueVisitor;
import org.modelinglab.ocl.core.values.utils.ValueVisitorAdapter;
import org.modelinglab.ocl.evaluator.walker.ExpressionWalker;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TupleAttributeCallExpEval {

    private TupleAttributeCallExpEval() {
    }

    public static TupleAttributeCallExpEval getInstance() {
        return TupleAttributeCallExpEvalHolder.INSTANCE;
    }

    public OclValue<?> evaluate(TupleAttributeCallExp exp, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException {
        OclValue<?> source = exp.getSource().accept(ExpressionWalker.getInstance(), runtimeEnv);

        ValueVisitor<OclValue<?>, TupleAttributeCallExp> switcher = TupleAttributeCallExpEval.SourceSwitcher.instance;
//        SwitchArgument<TupleAttributeCallExp> arg = new SwitchArgument<TupleAttributeCallExp>(exp, env);
        OclValue<?> result = source.accept(switcher, exp);
        
        runtimeEnv.getUserEvalEnv().getAnalyzer().analyze(exp, source, result);
        return result;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return TupleAttributeCallExpEval.getInstance();
    }

    private static class SourceSwitcher extends ValueVisitorAdapter<OclValue<?>, TupleAttributeCallExp> {

        public final static TupleAttributeCallExpEval.SourceSwitcher instance = new TupleAttributeCallExpEval.SourceSwitcher();

        @Override
        protected OclValue<?> defaultCase(OclValue val, TupleAttributeCallExp exp) {
            throw new AssertionError("It was expected that source was was "
                    + "evaluated to a tuple, null or invalid but result value is " + val + " "
                    + "(classifier " + val.getType() + ")!");
        }

        @Override
        public OclValue<?> visit(TupleValue source, TupleAttributeCallExp exp) {
            TupleType sourceType = source.getType();
            assert sourceType.conformsTo(exp.getSource().getType()) : "Runtime source type "
                    + "should conform to static exp.getSource().getType() by construction!";
            OclValue<?> result = source.getValue().get(exp.getTupleAttributeId());

            if (result == null) {
                throw new AssertionError("It was expected that " + exp.getSource() + " "
                        + "was a tuple with " + exp.getTupleAttributeId() + " attribute!");
            }
            assert result.getType().conformsTo(exp.getType()) :
                    "Runtime result type does not conform to static result type! (" + result.getType()
                    + " vs " + exp.getType() + ").";
            return result;
        }

        @Override
        public OclValue<?> visit(InvalidValue source, TupleAttributeCallExp exp) {
            return source;
        }

        @Override
        public OclValue<?> visit(VoidValue source, TupleAttributeCallExp arg) {
            return InvalidValue.instantiate();
        }
    }

    private static class TupleAttributeCallExpEvalHolder {

        private static final TupleAttributeCallExpEval INSTANCE = new TupleAttributeCallExpEval();

        private TupleAttributeCallExpEvalHolder() {
        }
    }
}
