/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator.SwitchArgument;

/**
 *
 */
abstract class DateOperationEvaluator extends OperationEvaluator {

    private final Operation evaluableOperation;
    private final Method method;

    public DateOperationEvaluator(Operation evaluableOperation, Method method) {
        this.evaluableOperation = evaluableOperation;
        this.method = method;
    }

    @Override
    public Operation getEvaluableOperation() {
        return evaluableOperation;
    }

    protected OclValue<?> evaluate(ObjectValue<?> val, SwitchArgument arg) {
        assert arg.arguments.size() == method.getParameterTypes().length;

        Object[] args = new Object[method.getParameterTypes().length];
        int i = 0;
        for (final OclValue<?> oclArg : arg.arguments) {
            if (oclArg.getType().oclIsUndefined()) { //by default if an argument is null or invalid, invalid is returned
                return InvalidValue.instantiate();
            }
            args[i] = DateUtils.translateToJavaObject(oclArg);
            i++;
        }

        Object result;
        try {
            Object castedVal;
            if (val == null)  {
                castedVal = null;
            }
            else {
                castedVal = DateUtils.translateToJavaObject(val);
            }
            result = method.invoke(castedVal, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
        return DateUtils.translateToOclObject(result, getEvaluableOperation().getType());
    }
}
