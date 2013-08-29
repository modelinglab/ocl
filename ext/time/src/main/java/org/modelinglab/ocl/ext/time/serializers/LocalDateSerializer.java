/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time.serializers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoField;

/**
 *
 */
public class LocalDateSerializer extends AbstractWrapperSerializer<Date, LocalDate> {

    private static final long MILLIS_IN_A_DAY = 24 * 60 * 60 * 1000;
    
    @Override
    protected Class<Date> getSerializedType() {
        return Date.class;
    }

    @Override
    protected Class<LocalDate> getJavaType() {
        return LocalDate.class;
    }

    @Override
    protected LocalDate unserialize(Date storedValue) {
        return LocalDate.ofEpochDay(storedValue.getTime() / MILLIS_IN_A_DAY);
    }

    @Override
    protected Date serialize(LocalDate wrapped) {
        return new Date(wrapped.getLong(ChronoField.EPOCH_DAY) * MILLIS_IN_A_DAY);
    }

    @Override
    public int getSqlType() {
        return java.sql.Types.DATE;
    }
}
