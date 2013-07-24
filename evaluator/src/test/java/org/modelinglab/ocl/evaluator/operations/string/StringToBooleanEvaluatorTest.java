/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.string.ToBoolean;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringToBooleanEvaluatorTest extends AbstractUnaryOperationTest {
    
    public StringToBooleanEvaluatorTest() {
        super(ToBoolean.getInstance());
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(new StringValue("asd"), BooleanValue.FALSE);
        executeTest(new StringValue("false"), BooleanValue.FALSE);
        executeTest(new StringValue("true"), BooleanValue.TRUE);
    }
}
