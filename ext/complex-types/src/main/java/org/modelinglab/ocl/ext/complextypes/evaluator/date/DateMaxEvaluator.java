/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateMax;

/**
 *
 */
public class DateMaxEvaluator extends AbstractDateEvaluator {

    @Override
    public OclValue<?> visitDate(AGDate.AGDateObject val, SwitchArgument arg) {
        OclValue<?> arg1 = arg.arguments.get(0);
        if (arg1.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        assert arg1.getValue() instanceof AGDate.AGDateObject;
        AGDate.AGDateObject otherDate = (AGDate.AGDateObject) arg1.getValue();
        
        if (val.getTime() < otherDate.getTime()) {
            return arg1;
        }
        else {
            return new ObjectValue<>(val);
        }
    }

    @Override
    public Operation getEvaluableOperation() {
        return DateMax.getInstance();
    }
}
