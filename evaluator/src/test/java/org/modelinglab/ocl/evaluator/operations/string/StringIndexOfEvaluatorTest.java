/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.string.IndexOf;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringIndexOfEvaluatorTest extends AbstractBinaryOperationTest {
    
    public StringIndexOfEvaluatorTest() {
        super(IndexOf.getInstance());
    }

    @Test
    public void test() throws Exception {
        String input = "123456789";
        String present = "3";
        String notPresent = "asd";
        String empty = "";
        
        StringValue inputVal = new StringValue(input);
        StringValue presentVal = new StringValue(present);
        StringValue notPresentVal = new StringValue(notPresent);
        StringValue emptyVal = new StringValue(empty);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, presentVal, invalid);
        executeTest(invalid, notPresentVal, invalid);
        executeTest(invalid, emptyVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, presentVal, invalid);
        executeTest(nullVal, notPresentVal, invalid);
        executeTest(nullVal, emptyVal, invalid);
        
        executeTest(inputVal, invalid, invalid);
        executeTest(inputVal, nullVal, invalid);
        executeTest(inputVal, presentVal, new IntegerValue(3l));
        executeTest(inputVal, notPresentVal, new IntegerValue(0l));
        executeTest(inputVal, emptyVal, new IntegerValue(1l));
        
        executeTest(emptyVal, invalid, invalid);
        executeTest(emptyVal, nullVal, invalid);
        executeTest(emptyVal, presentVal, new IntegerValue(0l));
        executeTest(emptyVal, notPresentVal, new IntegerValue(0l));
        executeTest(emptyVal, emptyVal, new IntegerValue(0l));
        
        
    }
}
