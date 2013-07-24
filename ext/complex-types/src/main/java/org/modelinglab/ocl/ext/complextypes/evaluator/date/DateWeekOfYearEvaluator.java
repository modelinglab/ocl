/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;
import org.modelinglab.ocl.ext.complextypes.classes.AGDayOfWeek;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateWeekOfYear;

/**
 *
 */
public class DateWeekOfYearEvaluator extends AbstractDateEvaluator {

    @Override
    public OclValue<?> visitDate(AGDate.AGDateObject val, SwitchArgument arg) {
        OclValue<?> arg1 = arg.arguments.get(0);
        if (arg1.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        assert arg1.getValue() instanceof UmlEnumLiteral;
        assert arg1.getType() instanceof AGDayOfWeek;
        UmlEnumLiteral oclLiteral = (UmlEnumLiteral) arg1.getValue();
        AGDayOfWeek.AGDayOfWeekEnum javaLiteral = AGDayOfWeek.AGDayOfWeekEnum.fromOclLiteral(oclLiteral);
        
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(val.getTime());
        c.setFirstDayOfWeek(javaLiteral.toJavaCalendarDay());
        
        return new IntegerValue(c.get(Calendar.WEEK_OF_YEAR));
    }
    
    

    @Override
    public Operation getEvaluableOperation() {
        return DateWeekOfYear.getInstance();
    }

}
