/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.integer;

import org.junit.Test;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.standard.operations.integer.Addition;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class IntegerAdditionEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {
    
    public IntegerAdditionEvaluatorTest() {
        super(Addition.getInstance());
    }

    /**
     *
     */
    @Test
    public void test() throws Exception {
        
        final long i1 = 5;
        final long i2 = 2;
        
        IntegerValue i1Val = new IntegerValue(i1);
        IntegerValue i2Val = new IntegerValue(i2);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, i1Val, invalid);
        executeTest(invalid, i2Val, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, i1Val, invalid);
        executeTest(nullVal, i2Val, invalid);
        
        executeTest(i1Val, invalid, invalid);
        executeTest(i1Val, nullVal, invalid);
        executeTest(i1Val, i1Val, new IntegerValue(i1 + i1));
        executeTest(i1Val, i2Val, new IntegerValue(i1 + i2));
        
        executeTest(i2Val, invalid, invalid);
        executeTest(i2Val, nullVal, invalid);
        executeTest(i2Val, i1Val, new IntegerValue(i1 + i2));
        executeTest(i2Val, i2Val, new IntegerValue(i2 + i2));
    }
}
