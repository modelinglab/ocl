/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bool;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.bool.And;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BooleanAndEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {

    public BooleanAndEvaluatorTest() {
        super(And.getInstance());
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, trueVal, invalid);
        executeTest(invalid, falseVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, trueVal, invalid);
        executeTest(nullVal, falseVal, falseVal);
        
        executeTest(falseVal, invalid, invalid);
        executeTest(falseVal, nullVal, falseVal);
        executeTest(falseVal, falseVal, falseVal);
        executeTest(falseVal, trueVal, falseVal);
        
        executeTest(trueVal, invalid, invalid);
        executeTest(trueVal, nullVal, invalid);
        executeTest(trueVal, falseVal, falseVal);
        executeTest(trueVal, trueVal, trueVal);
    }
    
}
