/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.serializers;

import org.threeten.bp.ZoneId;

/**
 *
 */
public class ZoneIdSerializer extends AbstractWrapperSerializer<String, ZoneId> {
    private static final long serialVersionUID = 1L;

    @Override
    protected Class<String> getSerializedType() {
        return String.class;
    }

    @Override
    protected Class<ZoneId> getJavaType() {
        return ZoneId.class;
    }

    @Override
    protected ZoneId unserialize(String storedValue) {
        return ZoneId.of(storedValue);
    }

    @Override
    protected String serialize(ZoneId wrapped) {
        return wrapped.getId();
    }

    @Override
    public int getSqlType() {
        return java.sql.Types.VARCHAR;
    }

}
