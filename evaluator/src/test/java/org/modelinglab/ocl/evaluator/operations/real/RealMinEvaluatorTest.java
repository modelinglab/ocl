/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.real.Min;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class RealMinEvaluatorTest extends AbstractBinaryOperationTest {
    
    public RealMinEvaluatorTest() {
        super(Min.getInstance());
    }

    @Test
    public void test()  throws Exception {
        final double r1 = 5;
        final double r2 = 2;
        final long i1 = 3;

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
        executeTest(r1Val, r1Val, r1Val);
        executeTest(r1Val, r2Val, new RealValue(Math.min(r1, r2)));
        executeTest(r1Val, i1Val, i1Val);

        executeTest(r2Val, invalid, invalid);
        executeTest(r2Val, nullVal, invalid);
        executeTest(r2Val, r1Val, new RealValue(Math.min(r1, r2)));
        executeTest(r2Val, r2Val, r2Val);
        executeTest(r2Val, i1Val, r2Val);
        
        executeTest(i1Val, invalid, invalid);
        executeTest(i1Val, nullVal, invalid);
        executeTest(i1Val, r1Val, i1Val);
        executeTest(i1Val, r2Val, r2Val);
        executeTest(i1Val, i1Val, i1Val); //TODO: This should fail. Integer operation should be executed
    }
}
