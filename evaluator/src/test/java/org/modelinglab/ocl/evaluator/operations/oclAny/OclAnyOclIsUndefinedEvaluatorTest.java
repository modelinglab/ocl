/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.oclAny.OclIsUndefined;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OclAnyOclIsUndefinedEvaluatorTest extends AbstractUnaryOperationTest {
    
    public OclAnyOclIsUndefinedEvaluatorTest() {
        super(OclIsUndefined.getInstance());
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, trueVal);
        executeTest(nullVal, trueVal);
        executeTest(trueVal, falseVal);
    }
}
