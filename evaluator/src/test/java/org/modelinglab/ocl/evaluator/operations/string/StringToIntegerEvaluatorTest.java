/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.string.ToInteger;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringToIntegerEvaluatorTest extends AbstractUnaryOperationTest {
    
    public StringToIntegerEvaluatorTest() {
        super(ToInteger.getInstance());
    }

    @Test
    public void test() throws Exception {
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(new StringValue("asd"), invalid);
        executeTest(new StringValue("123"), new IntegerValue(123l));
        executeTest(new StringValue("-123"), new IntegerValue(-123l));
    }
}
