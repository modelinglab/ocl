/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bool;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.bool.Implies;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BooleanImpliesEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {

    public BooleanImpliesEvaluatorTest() {
        super(Implies.getInstance());
    }
    
    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, trueVal, invalid);
        executeTest(invalid, falseVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, falseVal, invalid);
        executeTest(nullVal, trueVal, trueVal);
        
        executeTest(falseVal, invalid, invalid);
        executeTest(falseVal, nullVal, trueVal);
        executeTest(falseVal, falseVal, trueVal);
        executeTest(falseVal, trueVal, trueVal);
        
        executeTest(trueVal, invalid, invalid);
        executeTest(trueVal, nullVal, invalid);
        executeTest(trueVal, falseVal, falseVal);
        executeTest(trueVal, trueVal, trueVal);
    }
}
