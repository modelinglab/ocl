/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate.AGDateObject;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateHour;

/**
 *
 */
public class DateHourEvaluator extends AbstractDateEvaluator {

    @Override
    public OclValue<?> visitDate(AGDateObject val, SwitchArgument arg) {
        return new IntegerValue(val.getJodaDateTime().getHourOfDay());
    }

    @Override
    public Operation getEvaluableOperation() {
        return DateHour.getInstance();
    }

}
