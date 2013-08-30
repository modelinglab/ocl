/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.serializers;

import java.sql.Timestamp;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.temporal.ChronoField;

/**
 *
 */
public class LocalDateTimeSerializer extends AbstractWrapperSerializer<Timestamp, LocalDateTime>{

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
        Instant instant = Instant.ofEpochMilli(storedValue.getTime());
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    @Override
    protected Timestamp serialize(LocalDateTime wrapped) {
        Instant instant = wrapped.toInstant(ZoneOffset.UTC);
        return new Timestamp(instant.toEpochMilli());
    }

    @Override
    public int getSqlType() {
        return java.sql.Types.TIMESTAMP;
    }

}
