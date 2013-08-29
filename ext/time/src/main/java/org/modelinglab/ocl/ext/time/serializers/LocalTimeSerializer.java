/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.serializers;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import org.threeten.bp.LocalTime;

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
        return LocalTime.ofSecondOfDay(storedValue.getTime());
    }

    @Override
    protected Time serialize(LocalTime wrapped) {
        return new Time(wrapped.getSecond());
    }

    @Override
    public int getSqlType() {
        return java.sql.Types.TIME;
    }

}
