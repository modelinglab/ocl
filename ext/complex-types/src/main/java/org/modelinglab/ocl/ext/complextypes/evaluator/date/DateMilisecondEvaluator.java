/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateHour;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateMilisecond;

/**
 *
 */
public class DateMilisecondEvaluator extends AbstractDateEvaluator {

    @Override
    public OclValue<?> visitDate(AGDate.AGDateObject val, SwitchArgument arg) {
        return new IntegerValue(val.getJodaDateTime().getMillisOfSecond());
    }

    @Override
    public Operation getEvaluableOperation() {
        return DateMilisecond.getInstance();
    }
}
