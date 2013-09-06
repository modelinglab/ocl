/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time.serializers;

import org.junit.Test;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.temporal.ChronoUnit;

/**
 *
 */
public class InstantSerializerTest {
    
    private InstantSerializer serializer = new InstantSerializer();
    
    /**
     * Test of getSerializedType method, of class DurationSerializer.
     */
    @Test
    public void testGetSerializedType() {
        assert serializer.getSerializedType().equals(Long.class);
    }

    /**
     * Test of getJavaType method, of class DurationSerializer.
     */
    @Test
    public void testGetJavaType() {
        assert serializer.getJavaType().equals(Instant.class);
    }

    private void testUnserialize(long l) {
        Instant expected = Instant.ofEpochMilli(l);
        Instant result = serializer.unserialize(l);
        assert result.equals(expected) : "unserialize(" + l + ") = " + result + " != " + expected;
    }
    
    /**
     * Test of unserialize method, of class DurationSerializer.
     */
    @Test
    public void testUnserialize() {
        testUnserialize(0);
        testUnserialize(-2354l);
        testUnserialize(34567l);
    }

    private void testSerialize(long expected) {
        Instant input = Instant.ofEpochMilli(expected);
        long result = serializer.serialize(input);
        assert result == expected : "serialize(" + input + ") = " + result + " != " + expected;
    }
    
    /**
     * Test of serialize method, of class DurationSerializer.
     */
    @Test
    public void testSerialize() {
        testSerialize(0);
        testSerialize(-1023);
        testSerialize(66343241123l);
    }

    /**
     * Test of getSqlType method, of class DurationSerializer.
     */
    @Test
    public void testGetSqlType() {
        assert serializer.getSqlType() == java.sql.Types.BIGINT;
    }
}