/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.real.Abs;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class RealAbsEvaluatorTest extends AbstractUnaryOperationTest {
    
    public RealAbsEvaluatorTest() {
        super(Abs.getInstance());
    }

    @Test
    public void test() throws Exception {
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        
        RealValue zero = new RealValue(0d);
        RealValue neg = new RealValue(-2d);
        RealValue pos = new RealValue(2d);
        
        executeTest(zero, zero);
        executeTest(neg, pos);
        executeTest(pos, pos);
        
        //TODO: We should test that integers with this this operation name executes the overriden operation
    }
}
