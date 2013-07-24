/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.string.At;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringAtEvaluatorTest extends AbstractBinaryOperationTest {
    
    public StringAtEvaluatorTest() {
        super(At.getInstance());
    }

    @Test
    public void test() throws Exception {
        final String a = "a";
        
        StringValue aVal = new StringValue(a);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, new IntegerValue(2l), invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, new IntegerValue(2l), invalid);
        
        executeTest(aVal, invalid, invalid);
        executeTest(aVal, nullVal, invalid);
        executeTest(aVal, new IntegerValue(-1l), invalid);
        executeTest(aVal, new IntegerValue(0l), invalid); //OCL indexes starts at 1
        executeTest(aVal, new IntegerValue(1l), aVal);
        executeTest(aVal, new IntegerValue(2l), invalid);
    }
}
