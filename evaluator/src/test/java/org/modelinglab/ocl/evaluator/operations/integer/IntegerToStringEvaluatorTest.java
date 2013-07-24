/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.integer;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.integer.ToString;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class IntegerToStringEvaluatorTest extends AbstractUnaryOperationTest{
    
    public IntegerToStringEvaluatorTest() {
        super(ToString.getInstance());
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, new StringValue("null"));
        executeTest(new IntegerValue(0l), new StringValue("0"));
        executeTest(new IntegerValue(14l), new StringValue("14"));
        executeTest(new IntegerValue(-32l), new StringValue("-32"));
    }
}
