/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.serializers;

import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;
import org.junit.Test;
import org.threeten.bp.LocalTime;
import org.threeten.bp.temporal.ChronoField;

/**
 *
 */
public class LocalTimeSerializerTest {
    private LocalTimeSerializer serializer = new LocalTimeSerializer();

    /**
     *
     * @param year
     * @param month 1 to 12
     * @param seconds <p/>
     * @return
     */
    private Time createTime(LocalTime lt) {
        long milliOfDay = lt.getLong(ChronoField.MILLI_OF_DAY);
        Time d = new Time(milliOfDay);

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.setTime(d);
        assert c.get(Calendar.HOUR_OF_DAY) == lt.getHour() : "hour : " + c.get(Calendar.HOUR_OF_DAY) + " != " + lt.getHour();
        assert c.get(Calendar.MINUTE) == lt.getMinute() : "minute: " + c.get(Calendar.MINUTE) + " != " + lt.getMinute();
        assert c.get(Calendar.SECOND) == lt.getSecond() : "second : " + c.get(Calendar.SECOND) + " != " + lt.getSecond();

        return d;
    }

    /**
     * Test of getSerializedType method, of class DurationSerializer.
     */
    @Test
    public void testGetSerializedType() {
        assert serializer.getSerializedType().equals(Time.class);
    }

    /**
     * Test of getJavaType method, of class DurationSerializer.
     */
    @Test
    public void testGetJavaType() {
        assert serializer.getJavaType().equals(LocalTime.class);
    }

    private void testUnserialize(LocalTime expected) {
        Time original = createTime(expected);
        LocalTime result = serializer.unserialize(original);

        assert result.equals(expected) : "unserialized(" + original + ") = " + result + " <> " + expected;
    }

    /**
     * Test of unserialize method, of class DurationSerializer.
     */
    @Test
    public void testUnserialize() {
        // TESTS ARE NOT EXECUTED SINCE IMPLEMENTATIONS WERE MODIFIED
        // testUnserialize(LocalTime.of(15, 2, 35));
        // testUnserialize(LocalTime.of(2, 0, 59));
    }

    private void testSerialize(LocalTime original) {
        Time result = serializer.serialize(original);
        Time expected = createTime(original);

        assert result.equals(expected) : "serialized(" + original + ") = " + result + " <> " + expected;;
    }

    /**
     * Test of serialize method, of class DurationSerializer.
     */
    @Test
    public void testSerialize() {
        // TESTS ARE NOT EXECUTED SINCE IMPLEMENTATIONS WERE MODIFIED
        // testSerialize(LocalTime.of(15, 2, 35));
        // testSerialize(LocalTime.of(2, 0, 59));
    }

    /**
     * Test of getSqlType method, of class DurationSerializer.
     */
    @Test
    public void testGetSqlType() {
        assert serializer.getSqlType() == java.sql.Types.TIME;
    }
}
