/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.serializers;

import org.threeten.bp.Instant;

/**
 *
 */
public class InstantSerializer extends AbstractWrapperSerializer<Long, Instant> {
    private static final long serialVersionUID = 1L;

    @Override
    protected Class<Long> getSerializedType() {
        return Long.class;
    }

    @Override
    protected Class<Instant> getJavaType() {
        return Instant.class;
    }

    @Override
    protected Instant unserialize(Long storedValue) {
        return Instant.ofEpochMilli(storedValue);
    }

    @Override
    protected Long serialize(Instant wrapped) {
        return wrapped.toEpochMilli();
    }

    @Override
    public int getSqlType() {
        return java.sql.Types.BIGINT;
    }


}
