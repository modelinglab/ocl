/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.operations.date;

import java.util.Arrays;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;

/**
 *
 */
public class AllDateOperations {
    private AllDateOperations() {
    }

    public static List<Operation> getOperators() {
        return Arrays.asList(new Operation[] {
                DateAdd.getInstance(),
                DateDayOfMonth.getInstance(),
                DateDayOfWeek.getInstance(),
                DateDayOfYear.getInstance(),
                DateDiff.getInstance(),
                DateGreater.getInstance(),
                DateGreaterOrEqual.getInstance(),
                DateHour.getInstance(),
                DateLess.getInstance(),
                DateLessOrEqual.getInstance(),
                DateMax.getInstance(),
                DateMilisecond.getInstance(),
                DateMin.getInstance(),
                DateMinute.getInstance(),
                DateModule.getInstance(),
                DateMonth.getInstance(),
                DateNow.getInstance(),
                DateParse.getInstance(),
                DateSecond.getInstance(),
                DateToInteger.getInstance(),
                DateToString.getInstance(),
                DateTruncate.getInstance(),
                DateWeekOfYear.getInstance(),
                DateYear.getInstance()
        });
    }

}
