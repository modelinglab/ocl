/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time.serializers;

import java.util.HashMap;
import java.util.Map;
import org.modelinglab.ocl.utils.sql.SQLSerializable;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

/**
 *
 */
public class DateSQLSerializerProvider {

    private DateSQLSerializerProvider() {
    }

    public static Map<Class<?>, SQLSerializable> getSerializables() {
        HashMap<Class<?>, SQLSerializable> result = new HashMap<>(4);
        result.put(Duration.class, new DurationSerializer());
        result.put(LocalDate.class, new LocalDateSerializer());
        result.put(LocalDateTime.class, new LocalDateTimeSerializer());
        result.put(LocalTime.class, new LocalTimeSerializer());
        
        return result;
    }
}
