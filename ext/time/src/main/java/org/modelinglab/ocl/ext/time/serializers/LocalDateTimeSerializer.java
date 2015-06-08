/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.serializers;

import java.sql.Timestamp;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDateTime;

/**
 *
 */
public class LocalDateTimeSerializer extends AbstractWrapperSerializer<Timestamp, LocalDateTime>{
    private static final long serialVersionUID = 1L;

    @Override
    protected Class<Timestamp> getSerializedType() {
        return Timestamp.class;
    }

    @Override
    protected Class<LocalDateTime> getJavaType() {
        return LocalDateTime.class;
    }

    @Override
    protected LocalDateTime unserialize(Timestamp storedValue) {
        LocalDateTime ldt = DateTimeUtils.toLocalDateTime(storedValue);
        return ldt;
    }

    @Override
    protected Timestamp serialize(LocalDateTime wrapped) {
        Timestamp t = DateTimeUtils.toSqlTimestamp(wrapped);
        return t;
    }

    @Override
    public int getSqlType() {
        return java.sql.Types.TIMESTAMP;
    }

}
