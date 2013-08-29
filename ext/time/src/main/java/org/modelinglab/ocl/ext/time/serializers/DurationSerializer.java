/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.serializers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.threeten.bp.Duration;
import org.threeten.bp.temporal.ChronoUnit;

/**
 *
 */
public class DurationSerializer extends AbstractWrapperSerializer<Long, Duration> {

    @Override
    protected Class<Long> getSerializedType() {
        return Long.class;
    }

    @Override
    protected Class<Duration> getJavaType() {
        return Duration.class;
    }

    @Override
    protected Duration unserialize(Long storedValue) {
        return Duration.of(storedValue, ChronoUnit.SECONDS);
    }

    @Override
    protected Long serialize(Duration wrapped) {
        return wrapped.getSeconds();
    }

    @Override
    public int getSqlType() {
        return java.sql.Types.BIGINT;
    }

}
