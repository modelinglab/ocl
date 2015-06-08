/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time.serializers;

import java.sql.Date;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDate;

/**
 *
 */
public class LocalDateSerializer extends AbstractWrapperSerializer<Date, LocalDate> {
    private static final long serialVersionUID = 1L;

    // Not needed
    //private static final long MILLIS_IN_A_DAY = 24 * 60 * 60 * 1000;
    
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
        LocalDate ld = DateTimeUtils.toLocalDate(storedValue);
        return ld;
    }

    @Override
    protected Date serialize(LocalDate wrapped) {
        Date date = DateTimeUtils.toSqlDate(wrapped);
        return date;
    }

    @Override
    public int getSqlType() {
        return java.sql.Types.DATE;
    }
}
