/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.integer;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.integer.Negative;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class IntegerNegativeEvaluatorTest extends AbstractUnaryOperationTest {

    public IntegerNegativeEvaluatorTest() {
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
        executeTest(new IntegerValue(0l), new IntegerValue(0l));
        executeTest(new IntegerValue(1l), new IntegerValue(-1l));
        executeTest(new IntegerValue(-1l), new IntegerValue(1l));
    }
}
