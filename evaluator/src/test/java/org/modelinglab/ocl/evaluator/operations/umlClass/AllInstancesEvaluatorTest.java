/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.umlClass;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.umlClass.AllInstances;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllInstancesEvaluatorTest extends AbstractUnaryOperationTest {
    
    public AllInstancesEvaluatorTest() {
        super(AllInstances.createTemplateOperation());
    }

    @Test
    public void testSomeMethod() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        
        /*
         * the correctness of this operation depends on the correctness of the instances provider, so we
         * can not test the operation here.
         */
    }
    
    @Test
    public void testEnum() throws Exception {
        assertEquals("DaysOfWeek.allInstances()", "Set{"
                + "DaysOfWeek::MONDAY, "
                + "DaysOfWeek::TUESDAY, "
                + "DaysOfWeek::WEDNESDAY, "
                + "DaysOfWeek::THURSDAY, "
                + "DaysOfWeek::FRIDAY, "
                + "DaysOfWeek::SATURDAY, "
                + "DaysOfWeek::SUNDAY}");
    }
}
