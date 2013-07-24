/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import java.util.Calendar;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate.AGDateObject;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateDayOfMonth;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class DateDayOfMonthEvaluator extends AbstractDateEvaluator {

    @Override
    public OclValue<?> visitDate(AGDateObject val, SwitchArgument arg) {
        return new IntegerValue(val.getJodaDateTime().getDayOfMonth());
    }

    @Override
    public Operation getEvaluableOperation() {
        return DateDayOfMonth.getInstance();
    }
    
}
