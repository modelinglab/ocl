/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bool;

import org.junit.Test;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.standard.operations.bool.Xor;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BooleanXorEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {
    
    public BooleanXorEvaluatorTest() {
        super(Xor.getInstance());
    }

    @Test
    public void test() throws Exception {
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, falseVal, invalid);
        executeTest(invalid, trueVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, falseVal, invalid);
        executeTest(nullVal, trueVal, invalid);
        
        executeTest(falseVal, invalid, invalid);
        executeTest(falseVal, nullVal, invalid);
        executeTest(falseVal, falseVal, falseVal);
        executeTest(falseVal, trueVal, trueVal);
        
        executeTest(trueVal, invalid, invalid);
        executeTest(trueVal, nullVal, invalid);
        executeTest(trueVal, trueVal, falseVal);
        executeTest(trueVal, falseVal, trueVal);
    }
}
