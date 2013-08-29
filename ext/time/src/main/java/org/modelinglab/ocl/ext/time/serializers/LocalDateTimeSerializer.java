/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.serializers;

import java.sql.SQLException;
import java.sql.Timestamp;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;

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
        return LocalDateTime.ofEpochSecond(storedValue.getTime(), 0, ZoneOffset.UTC);
    }

    @Override
    protected Timestamp serialize(LocalDateTime wrapped) {
        return new Timestamp(wrapped.getLong(ChronoField.INSTANT_SECONDS));
    }

    @Override
    public int getSqlType() {
        return java.sql.Types.TIMESTAMP;
    }

}
