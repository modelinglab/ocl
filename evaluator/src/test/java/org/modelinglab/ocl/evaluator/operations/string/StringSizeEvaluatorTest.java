/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.string.Size;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringSizeEvaluatorTest extends AbstractUnaryOperationTest{
    
    public StringSizeEvaluatorTest() {
        super(Size.getInstance());
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(new StringValue(""), new IntegerValue(0l));
        executeTest(new StringValue("12345"), new IntegerValue(5l));
    }
}
