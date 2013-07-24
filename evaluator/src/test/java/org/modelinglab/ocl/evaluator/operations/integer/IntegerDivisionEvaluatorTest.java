/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.integer;

import org.junit.Test;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.standard.operations.integer.Division;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class IntegerDivisionEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {
    
    public IntegerDivisionEvaluatorTest() {
        super(Division.getInstance());
    }

    @Test
    public void test() throws Exception {
        
        final long i1 = 5;
        final long i2 = 2;
        
        RealValue r0Val = new RealValue(0d);
        IntegerValue i0Val = new IntegerValue(0l);
        IntegerValue i1Val = new IntegerValue(i1);
        IntegerValue i2Val = new IntegerValue(i2);
        
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, i0Val, invalid);
        executeTest(invalid, i1Val, invalid);
        executeTest(invalid, i2Val, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, i0Val, invalid);
        executeTest(nullVal, i1Val, invalid);
        executeTest(nullVal, i2Val, invalid);
        
        executeTest(i0Val, invalid, invalid);
        executeTest(i0Val, nullVal, invalid);
        executeTest(i0Val, i0Val, invalid);
        executeTest(i0Val, i1Val, r0Val);
        executeTest(i0Val, i2Val, r0Val);
        
        executeTest(i1Val, invalid, invalid);
        executeTest(i1Val, nullVal, invalid);
        executeTest(i1Val, i0Val, invalid);
        executeTest(i1Val, i1Val, new RealValue(1d));
        executeTest(i1Val, i2Val, new RealValue((double) i1 / i2));
        
        executeTest(i2Val, invalid, invalid);
        executeTest(i2Val, nullVal, invalid);
        executeTest(i2Val, i0Val, invalid);
        executeTest(i2Val, i1Val, new RealValue((double)i2 / i1));
        executeTest(i2Val, i2Val, new RealValue(1d));
    }
}
