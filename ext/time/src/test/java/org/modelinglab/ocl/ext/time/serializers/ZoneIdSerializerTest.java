/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time.serializers;

import org.junit.Test;
import org.threeten.bp.Duration;
import org.threeten.bp.ZoneId;
import org.threeten.bp.temporal.ChronoUnit;

/**
 *
 */
public class ZoneIdSerializerTest {

    private ZoneIdSerializer serializer = new ZoneIdSerializer();

    /**
     * Test of getSerializedType method, of class DurationSerializer.
     */
    @Test
    public void testGetSerializedType() {
        assert serializer.getSerializedType().equals(String.class);
    }

    /**
     * Test of getJavaType method, of class DurationSerializer.
     */
    @Test
    public void testGetJavaType() {
        assert serializer.getJavaType().equals(ZoneId.class);
    }

    /**
     * Test of unserialize method, of class DurationSerializer.
     */
    @Test
    public void testUnserialize() {
        assert serializer.unserialize("Z").equals(ZoneId.of("Z"));
        assert serializer.unserialize("UTC").equals(ZoneId.of("UTC"));
        assert serializer.unserialize("Europe/Madrid").equals(ZoneId.of("Europe/Madrid"));
        assert serializer.unserialize("America/New_York").equals(ZoneId.of("America/New_York"));
    }

    private void testSerialize(String id) {
        ZoneId zoneId = ZoneId.of(id);
        assert serializer.serialize(zoneId).equals(zoneId.getId()) : "serialize(" + zoneId + ") = " 
                                                                     + serializer.serialize(zoneId) 
                                                                     + " != " + zoneId.getId();
    }

    /**
     * Test of serialize method, of class DurationSerializer.
     */
    @Test
    public void testSerialize() {
        testSerialize("Z");
        testSerialize("UTC");
        testSerialize("Europe/Madrid");
        testSerialize("America/New_York");
    }

    /**
     * Test of getSqlType method, of class DurationSerializer.
     */
    @Test
    public void testGetSqlType() {
        assert serializer.getSqlType() == java.sql.Types.VARCHAR;
    }
}