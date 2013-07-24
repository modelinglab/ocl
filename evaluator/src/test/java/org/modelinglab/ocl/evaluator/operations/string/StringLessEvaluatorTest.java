/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.string.Less;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringLessEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {
    
    public StringLessEvaluatorTest() {
        super(Less.getInstance());
    }

    @Test
    public void test() throws Exception {
        final String a = "a";
        final String b = "b";
        
        StringValue aVal = new StringValue(a);
        StringValue bVal = new StringValue(b);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, aVal, invalid);
        executeTest(invalid, bVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, aVal, invalid);
        executeTest(nullVal, bVal, invalid);
        
        executeTest(aVal, invalid, invalid);
        executeTest(aVal, nullVal, invalid);
        executeTest(aVal, aVal, falseVal);
        executeTest(aVal, bVal, trueVal);
        
        executeTest(bVal, invalid, invalid);
        executeTest(bVal, nullVal, invalid);
        executeTest(bVal, aVal, falseVal);
        executeTest(bVal, bVal, falseVal);
    }
}
