/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.string.ToReal;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringToRealEvaluatorTest extends AbstractUnaryOperationTest {
    
    public StringToRealEvaluatorTest() {
        super(ToReal.getInstance());
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(new StringValue(""), invalid);
        executeTest(new StringValue("123"), new RealValue(123d));
        executeTest(new StringValue("12.3"), new RealValue(12.3));
        
        executeTest(new StringValue("-12.03"), new RealValue(-12.03));
        executeTest(new StringValue("-0.0"), new RealValue(0d));
    }
}
