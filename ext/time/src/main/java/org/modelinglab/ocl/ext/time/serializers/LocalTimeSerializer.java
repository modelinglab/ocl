/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.serializers;

import java.sql.Time;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalTime;

/**
 *
 */
public class LocalTimeSerializer extends AbstractWrapperSerializer<Time, LocalTime> {
    private static final long serialVersionUID = 1L;

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
        LocalTime lt = DateTimeUtils.toLocalTime(storedValue);
        return lt;
    }

    @Override
    protected Time serialize(LocalTime wrapped) {
        Time t = DateTimeUtils.toSqlTime(wrapped);
        return t;
    }

    @Override
    public int getSqlType() {
        return java.sql.Types.TIME;
    }

}
