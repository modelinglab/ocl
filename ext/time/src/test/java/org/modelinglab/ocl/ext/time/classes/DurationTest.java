/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.classes;

import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.ext.time.DateTestUtils;
import org.modelinglab.ocl.ext.time.DateUtils;
import org.threeten.bp.Duration;

/**
 *
 */
public class DurationTest {

    
    @Test
    public void ofSeconds() {
        Duration d = Duration.ofSeconds(2l);
        
        Classifier durationClassifier = DateUtils.translateToClassifier(Duration.class);
        
        assert DateTestUtils.equals("Duration.ofSeconds(2)", d);
    }
}
