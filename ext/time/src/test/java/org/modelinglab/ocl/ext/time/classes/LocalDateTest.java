/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.classes;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.vartables.VariableTable;
import org.modelinglab.ocl.ext.time.DateTestUtils;
import org.modelinglab.ocl.ext.time.DateUtils;
import org.modelinglab.ocl.ext.time.ExpectedOperationsTestUtil;
import org.threeten.bp.Clock;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

/**
 *
 */
public class LocalDateTest {

    @Test
    public void testExpectedOperations() throws NoSuchMethodException {
        Class<?> clazz = LocalDate.class;
        ExpectedOperationsTestUtil.test(clazz, Lists.newArrayList(clazz.getMethod("now", Clock.class)));
    }
    
    @Test
    public void now() {
        Clock c = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        Classifier clockClassifier = DateUtils.translateToClassifier(Clock.class);
        OclValue<?> cVal = DateUtils.translateToOclObject(c, clockClassifier);
        
        VariableTable varTable = new VariableTable();
        varTable.createVariable(new Variable("clock", null, clockClassifier), cVal);
        
        DateTestUtils.testEqual("LocalDate.now(clock)", LocalDate.now(c), varTable);
    }
    
}
