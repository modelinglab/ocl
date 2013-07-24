/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.oclAny.IsDifferent;
import org.modelinglab.ocl.core.standard.operations.oclAny.IsEqual;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OclAnyIsEqualEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest{
    
    public OclAnyIsEqualEvaluatorTest() {
        super(IsEqual.getInstance());
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, trueVal, invalid);
       
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, trueVal);
        executeTest(nullVal, trueVal, falseVal);
        
        executeTest(trueVal, invalid, invalid);
        executeTest(trueVal, nullVal, falseVal);
        executeTest(trueVal, falseVal, falseVal);
        executeTest(trueVal, trueVal, trueVal);
        
        executeTest(falseVal, invalid, invalid);
        executeTest(falseVal, nullVal, falseVal);
        executeTest(falseVal, falseVal, trueVal);
        executeTest(falseVal, trueVal, falseVal);
    }
}
