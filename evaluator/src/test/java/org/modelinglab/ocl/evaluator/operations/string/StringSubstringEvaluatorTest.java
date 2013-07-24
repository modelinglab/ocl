/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.string.Substring;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractTernaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringSubstringEvaluatorTest extends AbstractTernaryOperationTest {
    
    public StringSubstringEvaluatorTest() {
        super(Substring.getInstance());
    }

    @Test
    public void test() throws Exception {
        final String input = "123456789";
        final long i0 = 0l;
        final long i2 = 2l;
        final long i5 = 5l;
        final long i10 = 10l;
        
        StringValue inputVal = new StringValue(input);
        IntegerValue i0Val = new IntegerValue(i0);
        IntegerValue aValidFirstVal = new IntegerValue(i2);
        IntegerValue aValidLastVal = new IntegerValue(i5);
        IntegerValue i10Val = new IntegerValue(i10);
        
        executeTest(invalid, invalid, invalid, invalid);
        executeTest(invalid, nullVal, aValidLastVal, invalid);
        executeTest(invalid, aValidFirstVal, nullVal, invalid);
        
        executeTest(nullVal, invalid, invalid, invalid);
        executeTest(nullVal, nullVal, aValidLastVal, invalid);
        executeTest(nullVal, aValidFirstVal, nullVal, invalid);
        
        executeTest(inputVal, invalid, invalid, invalid);
        executeTest(inputVal, nullVal, aValidLastVal, invalid);
        executeTest(inputVal, aValidFirstVal, nullVal, invalid);
        
        executeTest(inputVal, i0Val, aValidLastVal, invalid);
        executeTest(inputVal, aValidFirstVal, i0Val, invalid);
        executeTest(inputVal, aValidFirstVal, i10Val, invalid);
        executeTest(inputVal, aValidFirstVal, aValidFirstVal, new StringValue(input.substring((int)i2 - 1, (int)i2)));
        executeTest(inputVal, aValidFirstVal, aValidLastVal, new StringValue(input.substring((int)i2 -1, (int)i5)));
        
        
    }
}
