/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import org.joda.time.DateTime;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.utils.sql.SQLSerializable;
import org.modelinglab.ocl.utils.sql.SQLSerializer;

/**
 * An AGDate is an UmlClass which represents a point in time. The implementation uses an integer attribute
 * with name "time" which represents the miliseconds since January 1, 1970, 00:00:00 GMT and offers several
 * operations to translate this attribute on other scales like years, months...
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class AGDate extends UmlClass {

    private static final long serialVersionUID = 1L;
    public static final String TIME_ATTR = "time";
    public static final AGDate INSTANCE = new AGDate();

    private AGDate() {
        setName("Date");

        Attribute att = new Attribute();
        att.setName("time"); //since January 1, 1970, 00:00:00 GMT
        att.setReferredType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        addOwnedAttribute(att);
        
        setAnnotation(SQLSerializable.class, new AGDateSQLSerializable());
    }

    public static AGDate getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return INSTANCE;
    }

    public static class AGDateObject implements UmlObject<Long> {

        private static final long serialVersionUID = 1L;
        IntegerValue id;

        public AGDateObject(Long milisecs) {
            id = new IntegerValue(milisecs);
        }

        public AGDateObject(Date d) {
            this(d.getTime());
        }

        public AGDateObject(DateTime dt) {
            this(dt.getMillis());
        }

        @Override
        public UmlClass getUmlClass() {
            return AGDate.INSTANCE;
        }

        @Override
        public Long getId() {
            return id.getValue();
        }

        public Date getDate() {
            return new Date(id.getValue());
        }

        public DateTime getJodaDateTime() {
            return new DateTime(id.getValue());
        }

        /**
         *
         * @return @see Date#getTime()
         */
        public Long getTime() {
            return id.getValue();
        }

        @Override
        public OclValue<?> getProperty(String propertyName) throws IllegalArgumentException, NotInitializedProperty {
            if (propertyName.equals(TIME_ATTR)) {
                return id;
            }
            throw new IllegalArgumentException(
                    getUmlClass() + " does not contain attributes called " + propertyName);
        }

        @Override
        public int hashCode() {
            int hash = 5;
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
            final AGDateObject other = (AGDateObject) obj;
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }
            return true;
        }
    }

    private static class AGDateSQLSerializable implements SQLSerializable {

        @Override
        public SQLSerializer getSerializer() {
            return AGDateSQLSerializer.INSTANCE;
        }

        @Override
        public int getSqlType() {
            return java.sql.Types.TIMESTAMP;
        }
        
    }
    
    private static class AGDateSQLSerializer implements SQLSerializer {

        private static AGDateSQLSerializer INSTANCE = new AGDateSQLSerializer();
        
        @Override
        public OclValue<?> fromSQL(ResultSet rs, String columnName, Classifier expectedType) throws SQLException {
            Date date = rs.getObject(columnName, Date.class);
            if (date == null) {
                return VoidValue.instantiate();
            }
            else {
                return new ObjectValue<>(new AGDateObject(date.getTime()));
            }
        }

        @Override
        public void toSQL(PreparedStatement ps, int columnIndex, OclValue<?> val, Classifier staticType) throws SQLException {
            assert val.getValue() instanceof AGDateObject;
            AGDateObject obj = (AGDateObject) val.getValue();
            
            Timestamp ts = new Timestamp(obj.getTime());
            ps.setTimestamp(columnIndex, ts);
        }
        
    }
}
