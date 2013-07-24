/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations;

import java.util.Iterator;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.core.values.utils.ValueVisitorAdapter;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class OperationEvaluator extends ValueVisitorAdapter<OclValue<?>, OperationEvaluator.SwitchArgument> {
    
    public abstract Operation getEvaluableOperation();

    /**
     * Return true if and only if the evaluator is implemented. Some evaluators are not implemented yet 
     * (because the semantic is not clear or because an efficient implementation is on way) but
     * the evaluator class is already created and stored in the operation evaluation table. When this method
     * returns true, the evaluator is implemented. If returns false, the evaluation is only "declared" but
     * it is very possible that an exception is thrown every time this evaluator is executed.
     * @return true iff the evaluator is implemented.
     */
    public boolean isImplemented() {
        return true;
    }
    
    public boolean canBeEvaluated(OperationCallExp op, OclValue sourceValue, List<OclValue<?>> arguments) {
        try {
            checkCanBeEvaluated(op, sourceValue, arguments);
        }
        catch (OclEvaluationException oee) {
            return false;
        }
        return true;
    }
    
    public void checkCanBeEvaluated(OperationCallExp op, OclValue sourceValue, List<OclValue<?>> arguments) throws OclEvaluationException {
        if (!isImplemented()) {
            throw new UnsupportedOperationException(op + " is not supported yet");
        }
        Operation evaluableOp = getEvaluableOperation();
        if (!evaluableOp.equals(op.getReferredOperation().getTemplateOperation())) {
            throw new OclEvaluationException(op, op.getReferredOperation().getSignature()+ " cannot be "
                    + "evaluated with the selected evaluator.");
        }
        if (evaluableOp.getOwnedParameters().size() != arguments.size()) {
            throw new OclEvaluationException(op, evaluableOp.getSignature() + " needs "
                    +evaluableOp.getOwnedParameters().size()+ " arguments, "+arguments.size()+" were supplied.");
        }
        Iterator<Parameter> paramIt = evaluableOp.getOwnedParameters().iterator();
        Iterator<OclValue<?>> argsIt = arguments.iterator();
        while (paramIt.hasNext()) { //implies args.hasNext()
            Parameter p = paramIt.next();
            OclValue arg = argsIt.next();
            
            if (!arg.getType().conformsTo(p.getType())) {
                throw new OclEvaluationException(op, "It is impossible to use "+arg+" (which type is "
                        + arg.getType()+") as value of "+p.getName()+" parameter.");
            }
        }
    }
    
    /**
     * This method is called when the source value does not conform with any visit method overrided
     * by OperationEvaluator subclasses. This can happen when subclass is not correctly implemented
     * or when source value does not conform with expected value, but this case should be detected
     * by evaluator implementation.
     * @param val
     * @param arg
     * @return 
     */
    @Override
    protected OclValue<?> defaultCase(OclValue val, SwitchArgument arg) {
        throw new AssertionError("Source was evaluated to " + val + " with type '"
                + val.getType() + "' does not conform to "+getEvaluableOperation().getSource()+".");
    }
    
    /**
     * By default, operations called over a InvalidValue source returns InvalidValue
     * 
     * @param val
     * @param arg
     * @return an instance of InvalidValue
     */
    @Override
    public OclValue<?> visit(InvalidValue val, SwitchArgument arg) {
        return val;
    }

    /**
     * By default, operations called over a VoidValue source returns InvalidValue
     * 
     * @param val
     * @param arg
     * @return an instance of InvalidValue
     */
    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        return InvalidValue.instantiate();
    }
    
    public static class SwitchArgument {
        public final OperationCallExp exp;
        public final List<OclValue<?>> arguments;
        public final EvaluatorVisitorArg runtimeEnv;

        public SwitchArgument(OperationCallExp exp, List<OclValue<?>> arguments, EvaluatorVisitorArg runtimeEnv) {
            this.exp = exp;
            this.arguments = arguments;
            this.runtimeEnv = runtimeEnv;
        }
    }
}
