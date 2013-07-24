/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateGreater;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateGreaterOrEqual;

/**
 *
 */
public class DateGreaterOrEqualEvaluator extends AbstractDateEvaluator {

    @Override
    public OclValue<?> visitDate(AGDate.AGDateObject val, OperationEvaluator.SwitchArgument arg) {
        OclValue<?> arg1 = arg.arguments.get(0);
        if (arg1.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        assert arg1.getValue() instanceof AGDate.AGDateObject;
        AGDate.AGDateObject otherDate = (AGDate.AGDateObject) arg1.getValue();
        
        return BooleanValue.createValue(val.getTime() >= otherDate.getTime());
    }

    @Override
    public Operation getEvaluableOperation() {
        return DateGreaterOrEqual.getInstance();
    }
}
