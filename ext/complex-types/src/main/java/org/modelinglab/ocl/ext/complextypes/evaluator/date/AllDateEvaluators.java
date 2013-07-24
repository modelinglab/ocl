/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import java.util.Arrays;
import java.util.List;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateAdd;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateDayOfMonth;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateDayOfWeek;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateDayOfYear;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateDiff;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateGreater;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateGreaterOrEqual;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateHour;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateLess;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateLessOrEqual;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateMax;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateMilisecond;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateMin;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateMinute;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateModule;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateMonth;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateNow;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateParse;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateSecond;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateToInteger;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateToString;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateTruncate;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateWeekOfYear;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateYear;

/**
 *
 */
public class AllDateEvaluators {
    private AllDateEvaluators() {
    }
    public static List<OperationEvaluator> getEvaluators() {
        return Arrays.asList(new OperationEvaluator[] {
                new DateAddEvaluator(),
                new DateDayOfMonthEvaluator(),
                new DateDayOfWeekEvaluator(),
                new DateDayOfYearEvaluator(),
                new DateDiffEvaluator(),
                new DateGreaterEvaluator(),
                new DateGreaterOrEqualEvaluator(),
                new DateHourEvaluator(),
                new DateLessEvaluator(),
                new DateLessOrEqualEvaluator(),
                new DateMaxEvaluator(),
                new DateMilisecondEvaluator(),
                new DateMinEvaluator(),
                new DateMinuteEvaluator(),
                new DateModuleEvaluator(),
                new DateMonthEvaluator(),
                new DateNowEvaluator(),
                new DateParseEvaluator(),
                new DateSecondEvaluator(),
                new DateToIntegerEvaluator(),
                new DateToStringEvaluator(),
                new DateTruncateEvaluator(),
                new DateWeekOfYearEvaluator(),
                new DateYearEvaluator()
        });
    }
}
