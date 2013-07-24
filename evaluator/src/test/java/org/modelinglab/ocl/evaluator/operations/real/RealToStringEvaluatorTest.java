/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.real.ToString;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class RealToStringEvaluatorTest extends AbstractUnaryOperationTest {
    
    public RealToStringEvaluatorTest() {
        super(ToString.getInstance());
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, new StringValue("null"));
        executeTest(new RealValue(0d), new StringValue("0.0"));
        executeTest(new RealValue(14d), new StringValue("14.0"));
        executeTest(new RealValue(-32d), new StringValue("-32.0"));
        //TODO: We should test that integers with this this operation name executes the overriden operation
    }
}
