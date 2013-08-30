/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time.serializers;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import org.junit.Test;
import org.threeten.bp.Clock;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.temporal.ChronoField;

/**
 *
 */
public class LocalDateTimeSerializerTest {

    private LocalDateTimeSerializer serializer = new LocalDateTimeSerializer();

    /**
     *
     * @param year
     * @param month 1 to 12
     * @param day
     * <p/>
     * @return
     */
    private Timestamp createTimestamp(LocalDateTime ldt) {
        Timestamp result = new Timestamp(ldt.toInstant(ZoneOffset.UTC).toEpochMilli());
        
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.setTime(result);
        
        assert c.get(Calendar.YEAR) == ldt.getYear() : "year : " + c.get(Calendar.YEAR) + " != " + ldt.getYear();
        assert c.get(Calendar.MONTH) + 1 == ldt.getMonthValue() : "month : " + (c.get(Calendar.MONTH) + 1) + " != " + ldt.getMonthValue();
        assert c.get(Calendar.DAY_OF_MONTH) == ldt.getDayOfMonth() : "day : " + c.get(Calendar.DAY_OF_MONTH) + " != " + ldt.getDayOfMonth();
        assert c.get(Calendar.HOUR_OF_DAY) == ldt.getHour() : "hour : " + c.get(Calendar.HOUR_OF_DAY) + " != " + ldt.getHour();
        assert c.get(Calendar.MINUTE) == ldt.getMinute() : "minute: " + c.get(Calendar.MINUTE) + " != " + ldt.getMinute();
        assert c.get(Calendar.SECOND) == ldt.getSecond() : "second : " + c.get(Calendar.SECOND) + " != " + ldt.getSecond();
        
        return result;
    }

    /**
     * Test of getSerializedType method, of class DurationSerializer.
     */
    @Test
    public void testGetSerializedType() {
        assert serializer.getSerializedType().equals(Timestamp.class);
    }

    /**
     * Test of getJavaType method, of class DurationSerializer.
     */
    @Test
    public void testGetJavaType() {
        assert serializer.getJavaType().equals(LocalDateTime.class);
    }

    private void testUnserialize(LocalDateTime expected) {
        Timestamp original = createTimestamp(expected);
        LocalDateTime result = serializer.unserialize(original);

        assert result.equals(expected) : "unserialized(" + original + ") = " + result + " <> " + expected;
    }

    /**
     * Test of unserialize method, of class DurationSerializer.
     */
    @Test
    public void testUnserialize() {
        testUnserialize(LocalDateTime.of(1902, Month.MARCH, 3, 2, 52, 24));
        testUnserialize(LocalDateTime.of(2102, Month.JULY, 1, 22, 2, 13));
    }

    private void testSerialize(LocalDateTime original) {
        Timestamp result = serializer.serialize(original);
        Timestamp expected = createTimestamp(original);
        Calendar c = Calendar.getInstance();
        c.setTime(result);

        assert result.equals(expected);
    }

    /**
     * Test of serialize method, of class DurationSerializer.
     */
    @Test
    public void testSerialize() {
        testSerialize(LocalDateTime.of(1902, Month.MARCH, 3, 2, 52, 24));
        testSerialize(LocalDateTime.of(2102, Month.JULY, 1, 22, 2, 13));
    }

    /**
     * Test of getSqlType method, of class DurationSerializer.
     */
    @Test
    public void testGetSqlType() {
        assert serializer.getSqlType() == java.sql.Types.TIMESTAMP;
    }
}
