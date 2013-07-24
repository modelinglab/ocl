/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.real.Negative;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class RealNegativeEvaluatorTest extends AbstractUnaryOperationTest {
    
    public RealNegativeEvaluatorTest() {
        super(Negative.getInstance());
    }

    @Override
    protected String getExpressionToParse(String sVarName) {
        return "-" + sVarName;
    }

    @Override
    protected String getLiteralExp(OclValue<?> sourceVal) {
        return "-" + sourceVal;
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(new RealValue(0d), new RealValue(0d));
        executeTest(new RealValue(1d), new RealValue(-1d));
        executeTest(new RealValue(-1d), new RealValue(1d));
        //TODO: We should test that integers with this this operation name executes the overriden operation
    }
}
