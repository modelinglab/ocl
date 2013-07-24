/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.integer;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.integer.Abs;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class IntegerAbsEvaluatorTest extends AbstractUnaryOperationTest {
    
    public IntegerAbsEvaluatorTest() {
        super(Abs.getInstance());
    }

    @Test
    public void test() throws Exception {
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        
        IntegerValue zero = new IntegerValue(0l);
        IntegerValue neg = new IntegerValue(-2l);
        IntegerValue pos = new IntegerValue(2l);
        
        executeTest(zero, zero);
        executeTest(neg, pos);
        executeTest(pos, pos);
    }
}
