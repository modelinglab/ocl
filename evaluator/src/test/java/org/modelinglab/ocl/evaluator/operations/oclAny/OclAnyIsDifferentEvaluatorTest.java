/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.oclAny.IsDifferent;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest.AbstractInfixOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OclAnyIsDifferentEvaluatorTest extends AbstractInfixOperationTest{
    
    public OclAnyIsDifferentEvaluatorTest() {
        super(IsDifferent.getInstance());
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, trueVal, invalid);
       
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, falseVal);
        executeTest(nullVal, trueVal, trueVal);
        
        executeTest(trueVal, invalid, invalid);
        executeTest(trueVal, nullVal, trueVal);
        executeTest(trueVal, falseVal, trueVal);
        executeTest(trueVal, trueVal, falseVal);
        
        executeTest(falseVal, invalid, invalid);
        executeTest(falseVal, nullVal, trueVal);
        executeTest(falseVal, falseVal, falseVal);
        executeTest(falseVal, trueVal, trueVal);
    }
}
