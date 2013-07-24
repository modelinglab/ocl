/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.classes;

import org.joda.time.DateTime;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AGTimeUnit extends UmlEnum {

    private static final long serialVersionUID = 1L;
    public static final AGTimeUnit INSTANCE = new AGTimeUnit();

    private AGTimeUnit() {
        setName("TimeUnit");

        addLiteral(new UmlEnumLiteral(AGTimeUnitEnum.ERA.name()));
        addLiteral(new UmlEnumLiteral(AGTimeUnitEnum.YEAR.name()));
        addLiteral(new UmlEnumLiteral(AGTimeUnitEnum.MONTH.name()));
        addLiteral(new UmlEnumLiteral(AGTimeUnitEnum.DAY.name()));
        addLiteral(new UmlEnumLiteral(AGTimeUnitEnum.HOUR.name()));
        addLiteral(new UmlEnumLiteral(AGTimeUnitEnum.MINUTE.name()));
        addLiteral(new UmlEnumLiteral(AGTimeUnitEnum.SECOND.name()));
        addLiteral(new UmlEnumLiteral(AGTimeUnitEnum.MILLISECOND.name()));
    }

    public static AGTimeUnit getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return INSTANCE;
    }

    public static enum AGTimeUnitEnum {

        ERA,
        YEAR,
        MONTH,
        DAY,
        HOUR,
        MINUTE,
        SECOND,
        MILLISECOND;

        public static AGTimeUnitEnum fromOclLiteral(UmlEnumLiteral oclLiteral) {
            if (!oclLiteral.getType().equals(AGTimeUnit.INSTANCE)) {
                throw new IllegalArgumentException(oclLiteral + " is not a literal of TimeUnit enum");
            }
            switch(oclLiteral.getOrdinal()) {
                case 0: return ERA;
                case 1: return YEAR;
                case 2: return MONTH;
                case 3: return DAY;
                case 4: return HOUR;
                case 5: return MINUTE;
                case 6: return SECOND;
                case 7: return MILLISECOND;
                default: throw new AssertionError("This method is not prepared to recive "+oclLiteral+", "
                        + "which ordinal is "+oclLiteral.getOrdinal());
            }
        }

        public UmlEnumLiteral toOclLiteral() {
            return AGTimeUnit.INSTANCE.getLiteral(this.ordinal());
        }
        
        public org.joda.time.DateTime.Property getJodaProperty(DateTime date) {
            switch(this) {
                case ERA: return date.era();
                case YEAR: return date.year();
                case MONTH: return date.monthOfYear();
                case DAY: return date.dayOfYear();
                case HOUR: return date.hourOfDay();
                case MINUTE: return date.minuteOfHour();
                case SECOND: return date.secondOfMinute();
                case MILLISECOND: return date.millisOfSecond();
                default: throw new AssertionError(this + " is an illegal value");
            }
        }
    }
}
