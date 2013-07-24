/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.string.EqualsIgnoreCase;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringEqualsIgnoreCaseEvaluatorTest extends AbstractBinaryOperationTest {
    
    public StringEqualsIgnoreCaseEvaluatorTest() {
        super(EqualsIgnoreCase.getInstance());
    }

    @Test
    public void test() throws Exception {
        final String a = "a";
        final String b = "b";
        
        StringValue aVal = new StringValue(a);
        StringValue _AVal = new StringValue(a.toUpperCase());
        StringValue bVal = new StringValue(b);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, aVal, invalid);
        executeTest(invalid, bVal, invalid);
        executeTest(invalid, _AVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, aVal, invalid);
        executeTest(nullVal, bVal, invalid);
        executeTest(nullVal, _AVal, invalid);
        
        executeTest(aVal, invalid, invalid);
        executeTest(aVal, nullVal, invalid);
        executeTest(aVal, aVal, trueVal);
        executeTest(aVal, bVal, falseVal);
        executeTest(aVal, _AVal, trueVal);
        
        executeTest(bVal, invalid, invalid);
        executeTest(bVal, nullVal, invalid);
        executeTest(bVal, aVal, falseVal);
        executeTest(bVal, bVal, trueVal);
        executeTest(bVal, _AVal, falseVal);
        
        executeTest(_AVal, invalid, invalid);
        executeTest(_AVal, nullVal, invalid);
        executeTest(_AVal, aVal, trueVal);
        executeTest(_AVal, bVal, falseVal);
        executeTest(_AVal, _AVal, trueVal);
    }
}
