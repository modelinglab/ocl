/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.serializers;

import java.sql.Time;
import org.threeten.bp.LocalTime;
import org.threeten.bp.temporal.ChronoField;

/**
 *
 */
public class LocalTimeSerializer extends AbstractWrapperSerializer<Time, LocalTime> {

    @Override
    protected Class<Time> getSerializedType() {
        return Time.class;
    }

    @Override
    protected Class<LocalTime> getJavaType() {
        return LocalTime.class;
    }

    @Override
    protected LocalTime unserialize(Time storedValue) {
        return LocalTime.ofSecondOfDay(storedValue.getTime() / 1000);
    }

    @Override
    protected Time serialize(LocalTime wrapped) {
        long milliOfDay = wrapped.getLong(ChronoField.MILLI_OF_DAY);
        return new Time(milliOfDay);
    }

    @Override
    public int getSqlType() {
        return java.sql.Types.TIME;
    }

}
