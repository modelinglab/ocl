/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time.serializers;

import java.sql.Date;
import java.util.Calendar;
import org.junit.Test;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;

/**
 *
 */
public class LocalDateSerializerTest {
    
    private LocalDateSerializer serializer = new LocalDateSerializer();
    
    /**
     * 
     * @param year
     * @param month 1 to 12
     * @param day
     * @return 
     */
    private Date createDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day);
        
        return new Date(c.getTimeInMillis());
    }
    
    /**
     * Test of getSerializedType method, of class DurationSerializer.
     */
    @Test
    public void testGetSerializedType() {
        assert serializer.getSerializedType().equals(Date.class);
    }

    /**
     * Test of getJavaType method, of class DurationSerializer.
     */
    @Test
    public void testGetJavaType() {
        assert serializer.getJavaType().equals(LocalDate.class);
    }

    private void testUnserialize(int year, int month, int day) {
        assert serializer.unserialize(createDate(year, month, day)).equals(LocalDate.of(year, month, day)) 
                : createDate(year, month, day) + " <> " + LocalDate.of(year, month, day);
    }
    
    /**
     * Test of unserialize method, of class DurationSerializer.
     */
    @Test
    public void testUnserialize() {
        testUnserialize(2010, 3, 21);
        testUnserialize(1800, 1, 12);
    }
    
    private void testSerialize(int year, int month, int day) {
        assert serializer.serialize(LocalDate.of(year, month, day)).equals(createDate(year, month, day))
                : createDate(year, month, day) + " <> " + LocalDate.of(year, month, day);;
    }

    /**
     * Test of serialize method, of class DurationSerializer.
     */
    @Test
    public void testSerialize() {
        testSerialize(1923, 2, 2);
        testSerialize(1990, 3, 12);
    }

    /**
     * Test of getSqlType method, of class DurationSerializer.
     */
    @Test
    public void testGetSqlType() {
        assert serializer.getSqlType() == java.sql.Types.DATE;
    }
}