/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.real.Greater;
import org.modelinglab.ocl.core.standard.operations.real.GreaterOrEqual;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class RealGreaterOrEqualEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {
    
    public RealGreaterOrEqualEvaluatorTest() {
        super(GreaterOrEqual.getInstance());
    }

    @Test
    public void test() throws Exception {
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
        executeTest(r1Val, r1Val, trueVal);
        executeTest(r1Val, r2Val, trueVal);
        executeTest(r1Val, i1Val, trueVal);

        executeTest(r2Val, invalid, invalid);
        executeTest(r2Val, nullVal, invalid);
        executeTest(r2Val, r1Val, falseVal);
        executeTest(r2Val, r2Val, trueVal);
        executeTest(r2Val, i1Val, falseVal);
        
        executeTest(i1Val, invalid, invalid);
        executeTest(i1Val, nullVal, invalid);
        executeTest(i1Val, r1Val, falseVal);
        executeTest(i1Val, r2Val, trueVal);
        executeTest(i1Val, i1Val, trueVal); //TODO: This should fail. Integer operation should be executed
    }
}
