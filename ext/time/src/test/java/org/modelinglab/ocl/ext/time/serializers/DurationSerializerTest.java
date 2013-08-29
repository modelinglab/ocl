/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time.serializers;

import org.junit.Test;
import org.threeten.bp.Duration;
import org.threeten.bp.temporal.ChronoUnit;

/**
 *
 */
public class DurationSerializerTest {
    
    private DurationSerializer serializer = new DurationSerializer();
    
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
        assert serializer.getJavaType().equals(Duration.class);
    }

    /**
     * Test of unserialize method, of class DurationSerializer.
     */
    @Test
    public void testUnserialize() {
        assert serializer.unserialize(30l).equals(Duration.of(30l, ChronoUnit.SECONDS));
        assert serializer.unserialize(120l).equals(Duration.of(2l, ChronoUnit.MINUTES));
        assert serializer.unserialize(60 * 60l).equals(Duration.of(1, ChronoUnit.HOURS));
        assert serializer.unserialize(60 * 60 * 24 * 2l).equals(Duration.of(2l, ChronoUnit.DAYS));
    }

    /**
     * Test of serialize method, of class DurationSerializer.
     */
    @Test
    public void testSerialize() {
        assert serializer.serialize(Duration.ZERO).equals(0l);
        assert serializer.serialize(Duration.ofSeconds(2l)).equals(2l);
        assert serializer.serialize(Duration.ofMinutes(3l)).equals(3l * 60);
        assert serializer.serialize(Duration.ofHours(4l)).equals(4l * 60 * 60);
        assert serializer.serialize(Duration.ofDays(5l)).equals(5l * 60 * 60 * 24);
    }

    /**
     * Test of getSqlType method, of class DurationSerializer.
     */
    @Test
    public void testGetSqlType() {
        assert serializer.getSqlType() == java.sql.Types.BIGINT;
    }
}