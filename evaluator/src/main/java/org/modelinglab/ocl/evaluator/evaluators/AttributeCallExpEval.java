/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.evaluators;

import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.ast.expressions.AttributeCallExp;
import org.modelinglab.ocl.core.values.utils.ValueVisitor;
import org.modelinglab.ocl.core.values.utils.ValueVisitorAdapter;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.walker.ExpressionWalker;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class AttributeCallExpEval {

    private AttributeCallExpEval() {
    }

    public static AttributeCallExpEval getInstance() {
        return AttributeCallExpEvalHolder.INSTANCE;
    }
    
    public OclValue<?> evaluate(AttributeCallExp exp, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException {
        OclValue<?> source = exp.getSource().accept(ExpressionWalker.getInstance(), runtimeEnv);

        /*
         * Check if source is correct
         */
        if (!source.getType().conformsTo(exp.getReferredAttribute().getUmlClass())) {
            throw new OclEvaluationException(exp, "It was expected that " + exp.getSource() + " was "
                    + "evaluated to a " + exp.getReferredAttribute().getUmlClass() + ".");
        }
        ValueVisitor<OclValue<?>, SwitchArgument<AttributeCallExp>> switcher = AttributeCallExpEval.SourceSwitcher.instance;
        SwitchArgument<AttributeCallExp> arg = new SwitchArgument<>(exp, runtimeEnv);
        
        OclValue<?> result = source.accept(switcher, arg);
        
        runtimeEnv.getUserEvalEnv().getAnalyzer().analyze(exp, source, result);
        return result;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return AttributeCallExpEval.getInstance();
    }

    private static class SourceSwitcher extends ValueVisitorAdapter<OclValue<?>, SwitchArgument<AttributeCallExp>> {

        public final static SourceSwitcher instance = new SourceSwitcher();
        
        @Override
        protected OclValue<?> defaultCase(OclValue<?> val, SwitchArgument<AttributeCallExp> arg) {
            throw new AssertionError("It was expected that source was evaluated to an object, null or "
                    + "invalid, not "+val.getType()+"!");
        }

        @Override
        public OclValue<?> visit(ObjectValue<?> val, SwitchArgument<AttributeCallExp> arg) {
            try {
                return val.getValue().getProperty(arg.expression.getReferredAttribute().getName());
            } catch (IllegalArgumentException | UmlObject.NotInitializedProperty ex) {
                throw new OclEvaluationException(arg.expression, ex);
            }
        }

        @Override
        public OclValue<?> visit(InvalidValue val, SwitchArgument<AttributeCallExp> arg) {
            return val;
        }

        @Override
        public OclValue<?> visit(VoidValue val, SwitchArgument<AttributeCallExp> arg) {
            return InvalidValue.instantiate();
        }
        
    }

    

    private static class AttributeCallExpEvalHolder {
        private static final AttributeCallExpEval INSTANCE = new AttributeCallExpEval();
    }
 }
