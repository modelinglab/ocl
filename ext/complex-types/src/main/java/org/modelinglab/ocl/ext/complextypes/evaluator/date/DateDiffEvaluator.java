/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.Seconds;
import org.joda.time.Years;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate.AGDateObject;
import org.modelinglab.ocl.ext.complextypes.classes.AGTimeUnit.AGTimeUnitEnum;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateDiff;

/**
 *
 */
public class DateDiffEvaluator extends AbstractDateEvaluator {

    @Override
    public OclValue<?> visitDate(AGDateObject val, SwitchArgument arg) {
        OclValue<?> arg1 = arg.arguments.get(0);
        if (arg1.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        assert arg1.getValue() instanceof AGDateObject;
        AGDateObject subtrahend = (AGDateObject) arg1.getValue();

        OclValue<?> arg2 = arg.arguments.get(1);
        if (arg2.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        assert arg2.getValue() instanceof UmlEnumLiteral;
        AGTimeUnitEnum tu = AGTimeUnitEnum.fromOclLiteral((UmlEnumLiteral) arg2.getValue());

        long unitsBetween = getUnitsBetween(subtrahend.getJodaDateTime(), val.getJodaDateTime(), tu);
        
        return new IntegerValue(unitsBetween);
    }
    
    private long getUnitsBetween(DateTime start, DateTime end, AGTimeUnitEnum timeUnit) {
        switch (timeUnit) {
            case YEAR:
                return Years.yearsBetween(start, end).getYears();
            case MONTH:
                return Months.monthsBetween(start, end).getMonths();
            case DAY:
                return Days.daysBetween(start, end).getDays();
            case HOUR:
                return Hours.hoursBetween(start, end).getHours();
            case MINUTE:
                return Minutes.minutesBetween(start, end).getMinutes();
            case SECOND:
                return Seconds.secondsBetween(start, end).getSeconds();
            case MILLISECOND:
                return end.getMillis() - start.getMillis();
            default:
                throw new AssertionError(this + " is an illegal value");
        }
    }

    @Override
    public Operation getEvaluableOperation() {
        return DateDiff.getInstance();
    }
}
