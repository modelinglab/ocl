/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.classes;

import org.junit.Test;
import org.modelinglab.ocl.ext.time.DateTestUtils;
import org.threeten.bp.Instant;

/**
 *
 */
public class InstantDate {

    @Test
    public void testParse() throws Exception {
        String format = "1995-10-23T10:12:35Z";
        Instant instant = Instant.parse(format);
        
        assert DateTestUtils.equals("Instant.parse(\"" + format + "\")", instant);
    }
}
