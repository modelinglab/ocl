/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.classes;

import java.util.Calendar;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AGDayOfWeek extends UmlEnum {

    private static final long serialVersionUID = 1L;
    public static final AGDayOfWeek INSTANCE = new AGDayOfWeek();

    private AGDayOfWeek() {
        setName("DayOfWeek");

        addLiteral(new UmlEnumLiteral("MONDAY"));
        addLiteral(new UmlEnumLiteral("TUESDAY"));
        addLiteral(new UmlEnumLiteral("WEDNESDAY"));
        addLiteral(new UmlEnumLiteral("THURSDAY"));
        addLiteral(new UmlEnumLiteral("FRIDAY"));
        addLiteral(new UmlEnumLiteral("SATURDAY"));
        addLiteral(new UmlEnumLiteral("SUNDAY"));

    }

    public static AGDayOfWeek getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return INSTANCE;
    }

    public static enum AGDayOfWeekEnum {

        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;

        public static AGDayOfWeekEnum fromOclLiteral(UmlEnumLiteral oclLiteral) {
            if (!oclLiteral.getType().equals(AGTimeUnit.INSTANCE)) {
                throw new IllegalArgumentException(oclLiteral + " is not a literal of DayOfWeek enum");
            }
            switch (oclLiteral.getOrdinal()) {
                case 0:
                    return MONDAY;
                case 1:
                    return TUESDAY;
                case 2:
                    return WEDNESDAY;
                case 3:
                    return THURSDAY;
                case 4:
                    return FRIDAY;
                case 5:
                    return SATURDAY;
                case 6:
                    return SUNDAY;
                default:
                    throw new AssertionError("This method is not prepared to recive " + oclLiteral + ", "
                            + "which ordinal is " + oclLiteral.getOrdinal());
            }
        }

        public UmlEnumLiteral toOclLiteral() {
            return AGDayOfWeek.INSTANCE.getLiteral(this.ordinal());
        }

        public int toJavaCalendarDay() {
            switch (this) {
                case MONDAY: return Calendar.MONDAY;
                case TUESDAY: return Calendar.TUESDAY;
                case WEDNESDAY: return Calendar.WEDNESDAY;
                case THURSDAY: return Calendar.THURSDAY;
                case FRIDAY: return Calendar.FRIDAY;
                case SATURDAY: return Calendar.SATURDAY;
                case SUNDAY: return Calendar.SUNDAY;
                default : throw new AssertionError(this +" is an invalid value");
            }
        }
    }
}
