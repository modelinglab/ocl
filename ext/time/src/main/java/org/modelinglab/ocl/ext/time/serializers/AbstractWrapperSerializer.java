/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time.serializers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.ext.time.DateUtils;
import org.modelinglab.ocl.ext.time.UmlWrapperObject;
import org.modelinglab.ocl.utils.sql.SQLSerializable;
import org.modelinglab.ocl.utils.sql.SQLSerializer;
import org.threeten.bp.Duration;

/**
 *
 */
public abstract class AbstractWrapperSerializer<S, W> implements SQLSerializable, SQLSerializer {
    protected abstract Class<S> getSerializedType();

    protected abstract Class<W> getJavaType();

    protected abstract W unserialize(S storedValue);

    protected abstract S serialize(W wrapped);

    @Override
    public SQLSerializer getSerializer() {
        return this;
    }

    @Override
    public OclValue<?> fromSQL(ResultSet rs, String columnName, Classifier expectedType) throws SQLException {
        S value = rs.getObject(columnName, getSerializedType());
        if (value == null) {
            return VoidValue.instantiate();
        }
        else {
            W wrapped = unserialize(value);
            return DateUtils.translateToOclObject(value, expectedType);
        }
    }

    @Override
    public void toSQL(PreparedStatement ps, int columnIndex, OclValue<?> val, Classifier staticType) throws SQLException {

        assert val.getValue() instanceof UmlWrapperObject;
        UmlWrapperObject obj = (UmlWrapperObject) val.getValue();

        assert getJavaType().isInstance(obj.getWrappedObject());
        W wrapped = getJavaType().cast(obj.getWrappedObject());

        S converted = serialize(wrapped);
        ps.setObject(columnIndex, converted, getSqlType());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }
}
