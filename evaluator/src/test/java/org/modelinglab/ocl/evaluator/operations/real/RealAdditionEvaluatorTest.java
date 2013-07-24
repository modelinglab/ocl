/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.real.Addition;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class RealAdditionEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {
    
    public RealAdditionEvaluatorTest() {
        super(Addition.getInstance());
    }

    @Test
    public void test() throws Exception {
        
        final double r1 = 5;
        final double r2 = 2;
        final long i1 = 100;
        
        RealValue r1Val = new RealValue(r1);
        RealValue r2Val = new RealValue(r2);
        IntegerValue i1Val = new IntegerValue(i1);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, r1Val, invalid);
        executeTest(invalid, r2Val, invalid);
        executeTest(invalid, i1Val, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, r1Val, invalid);
        executeTest(nullVal, r2Val, invalid);
        executeTest(invalid, i1Val, invalid);
        
        executeTest(r1Val, invalid, invalid);
        executeTest(r1Val, nullVal, invalid);
        executeTest(r1Val, r1Val, new RealValue(r1 + r1));
        executeTest(r1Val, r2Val, new RealValue(r1 + r2));
        executeTest(r1Val, i1Val, new RealValue(r1 + i1));
        
        executeTest(r2Val, invalid, invalid);
        executeTest(r2Val, nullVal, invalid);
        executeTest(r2Val, r1Val, new RealValue(r1 + r2));
        executeTest(r2Val, r2Val, new RealValue(r2 + r2));
        executeTest(r2Val, i1Val, new RealValue(r2 + i1));
        
        executeTest(i1Val, invalid, invalid);
        executeTest(i1Val, nullVal, invalid);
        executeTest(i1Val, r1Val, new RealValue(i1 + r1));
        executeTest(i1Val, r2Val, new RealValue(i1 + r2));
        executeTest(i1Val, i1Val, new RealValue((double)i1 + i1)); //TODO: This should fail. Integer operation should be executed
    }
}
