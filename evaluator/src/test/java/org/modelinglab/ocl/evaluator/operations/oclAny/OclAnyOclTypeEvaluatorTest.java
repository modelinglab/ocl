/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.core.standard.operations.oclAny.OclType;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OclAnyOclTypeEvaluatorTest extends AbstractUnaryOperationTest {
    
    public OclAnyOclTypeEvaluatorTest() {
        super(OclType.createTemplateOperation());
    }

    @Test
    public void test() throws Exception {
        ClassifierValue intClassifier = new ClassifierValue(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        ClassifierValue realClassifier = new ClassifierValue(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL));
        ClassifierValue voidClassifier = new ClassifierValue(VoidType.getInstance());

        RealValue r1 = new RealValue(10d);
        IntegerValue i1 = new IntegerValue(10l);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, voidClassifier);
        executeTest(r1, realClassifier);
        executeTest(i1, intClassifier);
    }
}
