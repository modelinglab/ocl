/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate.AGDateObject;
import org.modelinglab.ocl.ext.complextypes.classes.AGTimeUnit;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateTruncate;

/**
 *
 */
public class DateTruncateEvaluator extends AbstractDateEvaluator {

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

        Collection<AGTimeUnit.AGTimeUnitEnum> greaterUnits = getLowerUnits(tu);
        for (final AGTimeUnit.AGTimeUnitEnum timeUnitEnum : greaterUnits) {
            Property jodaProperty = timeUnitEnum.getJodaProperty(moduledTime);
            moduledTime = jodaProperty.setCopy(jodaProperty.getMinimumValue());
        }
        return new ObjectValue<>(new AGDateObject(moduledTime));
    }

    private Collection<AGTimeUnit.AGTimeUnitEnum> getLowerUnits(AGTimeUnit.AGTimeUnitEnum tu) {
        switch (tu) {
            case ERA:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnit.AGTimeUnitEnum.YEAR,
                            AGTimeUnit.AGTimeUnitEnum.MONTH,
                            AGTimeUnit.AGTimeUnitEnum.DAY,
                            AGTimeUnit.AGTimeUnitEnum.HOUR,
                            AGTimeUnit.AGTimeUnitEnum.MINUTE,
                            AGTimeUnit.AGTimeUnitEnum.SECOND,
                            AGTimeUnit.AGTimeUnitEnum.MILLISECOND});
            case YEAR:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnit.AGTimeUnitEnum.MONTH,
                            AGTimeUnit.AGTimeUnitEnum.DAY,
                            AGTimeUnit.AGTimeUnitEnum.HOUR,
                            AGTimeUnit.AGTimeUnitEnum.MINUTE,
                            AGTimeUnit.AGTimeUnitEnum.SECOND,
                            AGTimeUnit.AGTimeUnitEnum.MILLISECOND});
            case MONTH:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnit.AGTimeUnitEnum.DAY,
                            AGTimeUnit.AGTimeUnitEnum.HOUR,
                            AGTimeUnit.AGTimeUnitEnum.MINUTE,
                            AGTimeUnit.AGTimeUnitEnum.SECOND,
                            AGTimeUnit.AGTimeUnitEnum.MILLISECOND});
            case DAY:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnit.AGTimeUnitEnum.HOUR,
                            AGTimeUnit.AGTimeUnitEnum.MINUTE,
                            AGTimeUnit.AGTimeUnitEnum.SECOND,
                            AGTimeUnit.AGTimeUnitEnum.MILLISECOND});
            case HOUR:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnit.AGTimeUnitEnum.MINUTE,
                            AGTimeUnit.AGTimeUnitEnum.SECOND,
                            AGTimeUnit.AGTimeUnitEnum.MILLISECOND});
            case MINUTE:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {
                            AGTimeUnit.AGTimeUnitEnum.SECOND,
                            AGTimeUnit.AGTimeUnitEnum.MILLISECOND});
            case SECOND:
                return Arrays.asList(new AGTimeUnit.AGTimeUnitEnum[] {AGTimeUnit.AGTimeUnitEnum.MILLISECOND});
            case MILLISECOND:
                return Collections.emptyList();
            default:
                throw new AssertionError(this + " is an illegal value");
        }
    }

    @Override
    public Operation getEvaluableOperation() {
        return DateTruncate.getInstance();
    }
}
