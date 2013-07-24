/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate.AGDateObject;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateNow;

/**
 *
 */
public class DateNowEvaluator extends OperationEvaluator {

    @Override
    public OclValue<?> visit(ClassifierValue val, SwitchArgument arg) {
        if (!(val.getValue() instanceof AGDate)) {
            throw new IllegalArgumentException("This evaluator expects that source was Date.");
        }
        return new ObjectValue<>(new AGDateObject(System.currentTimeMillis()));
    }

    
    @Override
    public Operation getEvaluableOperation() {
        return DateNow.getInstance();
    }

}
