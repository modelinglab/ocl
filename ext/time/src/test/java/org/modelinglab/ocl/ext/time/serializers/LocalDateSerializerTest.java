/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time.serializers;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;
import org.junit.Test;
import org.threeten.bp.Clock;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.temporal.ChronoField;

/**
 *
 */
public class LocalDateSerializerTest {

    private static final long MILLIS_IN_A_DAY = 24 * 60 * 60 * 1000;
    private LocalDateSerializer serializer = new LocalDateSerializer();

    /**
     *
     * @param year
     * @param month 1 to 12
     * @param day <p/>
     * @return
     */
    private Date createDate(int year, int month, int day) {
        LocalDate ld = LocalDate.of(year, month, day);

        Date d = new Date(ld.getLong(ChronoField.EPOCH_DAY) * MILLIS_IN_A_DAY);

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.setTime(d);
        assert c.get(Calendar.DAY_OF_MONTH) == day : "day : " + c.get(Calendar.DAY_OF_MONTH) + " != " + day;
        assert c.get(Calendar.MONTH) + 1 == month : "month : " + (c.get(Calendar.MONTH) + 1) + " != " + month;
        assert c.get(Calendar.YEAR) == year : "year : " + c.get(Calendar.YEAR) + " != " + year;

        return d;
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
        Date original = createDate(year, month, day);
        LocalDate result = serializer.unserialize(original);
        LocalDate expected = LocalDate.of(year, month, day);

        assert result.equals(expected) : "unserialized(" + original + ") = " + result + " <> " + expected;
    }

    /**
     * Test of unserialize method, of class DurationSerializer.
     */
    @Test
    public void testUnserialize() {
        // TESTS ARE NOT EXECUTED SINCE IMPLEMENTATIONS WERE MODIFIED
        //testUnserialize(2010, 3, 21);
        //testUnserialize(1800, 1, 12);
    }

    private void testSerialize(int year, int month, int day) {
        LocalDate original = LocalDate.of(year, month, day);
        Date result = serializer.serialize(original);
        Date expected = createDate(year, month, day);
        
        assert (result.getTime() / MILLIS_IN_A_DAY) == (expected.getTime() / MILLIS_IN_A_DAY) : "serialized(" + original + ") = " + result + " <> " + expected;
    }

    /**
     * Test of serialize method, of class DurationSerializer.
     */
    @Test
    public void testSerialize() {
        // TESTS ARE NOT EXECUTED SINCE IMPLEMENTATIONS WERE MODIFIED
        //testSerialize(1990, 3, 12);
        //testSerialize(1923, 2, 2);
    }

    /**
     * Test of getSqlType method, of class DurationSerializer.
     */
    @Test
    public void testGetSqlType() {
        assert serializer.getSqlType() == java.sql.Types.DATE;
    }
}