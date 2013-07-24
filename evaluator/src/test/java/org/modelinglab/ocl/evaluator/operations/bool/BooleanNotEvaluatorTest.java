/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bool;

import org.junit.Test;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.standard.operations.bool.Not;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BooleanNotEvaluatorTest extends AbstractUnaryOperationTest {
    
    public BooleanNotEvaluatorTest() {
        super(Not.getInstance());
    }

    @Override
    protected String getExpressionToParse(String source) {
        return "not "+source;
    }

    @Override
    protected String getLiteralExp(OclValue<?> sourceVal) {
        return "not "+sourceVal;
    }

    @Test
    public void test() throws Exception {
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(trueVal, falseVal);
        executeTest(falseVal, trueVal);
    }
}
