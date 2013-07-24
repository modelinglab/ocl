/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.joda.time.DateTime;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate.AGDateObject;
import org.modelinglab.ocl.ext.complextypes.classes.AGTimeUnit;
import org.modelinglab.ocl.ext.complextypes.classes.AGTimeUnit.AGTimeUnitEnum;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateModule;

/**
 *
 */
public class DateModuleEvaluator extends AbstractDateEvaluator {

    @Override
    public OclValue<?> visitDate(AGDateObject val, SwitchArgument arg) {
        OclValue<?> arg1 = arg.arguments.get(0);
        if (arg1.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        assert arg1.getValue() instanceof UmlEnumLiteral;
        AGTimeUnit.AGTimeUnitEnum tu = AGTimeUnit.AGTimeUnitEnum.fromOclLiteral((UmlEnumLiteral) arg1.
                getValue());

        DateTime moduledTime = val.getJodaDateTime();

        Collection<AGTimeUnitEnum> greaterUnits = getGreaterUnits(tu);
        for (final AGTimeUnitEnum timeUnitEnum : greaterUnits) {
            moduledTime = timeUnitEnum.getJodaProperty(moduledTime).setCopy(0);
        }
        return new ObjectValue<>(new AGDateObject(moduledTime));
    }

    private Collection<AGTimeUnitEnum> getGreaterUnits(AGTimeUnitEnum tu) {
        switch (tu) {
            case ERA:
                return Collections.emptyList();
            case YEAR:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {AGTimeUnitEnum.ERA});
            case MONTH:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnitEnum.ERA,
                            AGTimeUnitEnum.YEAR});
            case DAY:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnitEnum.ERA,
                            AGTimeUnitEnum.YEAR,
                            AGTimeUnitEnum.MONTH});
            case HOUR:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnitEnum.ERA,
                            AGTimeUnitEnum.YEAR,
                            AGTimeUnitEnum.MONTH,
                            AGTimeUnitEnum.DAY});
            case MINUTE:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnitEnum.ERA,
                            AGTimeUnitEnum.YEAR,
                            AGTimeUnitEnum.MONTH,
                            AGTimeUnitEnum.DAY,
                            AGTimeUnitEnum.HOUR});
            case SECOND:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnitEnum.ERA,
                            AGTimeUnitEnum.YEAR,
                            AGTimeUnitEnum.MONTH,
                            AGTimeUnitEnum.DAY,
                            AGTimeUnitEnum.HOUR,
                            AGTimeUnitEnum.MINUTE});
            case MILLISECOND:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnitEnum.ERA,
                            AGTimeUnitEnum.YEAR,
                            AGTimeUnitEnum.MONTH,
                            AGTimeUnitEnum.DAY,
                            AGTimeUnitEnum.HOUR,
                            AGTimeUnitEnum.MINUTE,
                            AGTimeUnitEnum.SECOND});
            default:
                throw new AssertionError(this + " is an illegal value");
        }
    }

    @Override
    public Operation getEvaluableOperation() {
        return DateModule.getInstance();
    }
}
