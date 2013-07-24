/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.real.Floor;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class RealFloorEvaluatorTest extends AbstractUnaryOperationTest {
    
    public RealFloorEvaluatorTest() {
        super(Floor.getInstance());
    }

    @Test
    public void test() throws Exception {
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(new RealValue(23.2), new IntegerValue(23l));
        executeTest(new RealValue(-23.2), new IntegerValue(-24l));
        executeTest(new RealValue(-0d), new IntegerValue(0l));
        
        executeTest(new IntegerValue(19l), new IntegerValue(19l));
        executeTest(new IntegerValue(-23l), new IntegerValue(-23l));
        executeTest(new IntegerValue(0l), new IntegerValue(0l));
    }
}
