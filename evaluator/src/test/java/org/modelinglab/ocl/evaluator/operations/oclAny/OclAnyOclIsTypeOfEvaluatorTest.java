/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.junit.Test;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.core.standard.operations.oclAny.OclIsKindOf;
import org.modelinglab.ocl.core.standard.operations.oclAny.OclIsTypeOf;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OclAnyOclIsTypeOfEvaluatorTest extends AbstractBinaryOperationTest {

    public OclAnyOclIsTypeOfEvaluatorTest() {
        super(new OclIsTypeOf(OclIsTypeOf.createTemplateOperation()));
    }

    @Override
    protected Classifier getSourceType(Operation op) {
        return PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL);
    }

    @Override
    protected Classifier getArgType(Operation op) {
        return PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL).getClassifierType();
    }

    @Test
    public void test() throws Exception {
        ClassifierValue intClassifier = new ClassifierValue(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        ClassifierValue realClassifier = new ClassifierValue(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL));
        ClassifierValue voidClassifier = new ClassifierValue(VoidType.getInstance());

        RealValue r1 = new RealValue(10d);
        IntegerValue i1 = new IntegerValue(10l);

        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, realClassifier, invalid);
        executeTest(invalid, intClassifier, invalid);

        executeTest(i1, invalid, invalid);
        executeTest(i1, nullVal, invalid);
        executeTest(i1, realClassifier, falseVal);
        executeTest(i1, intClassifier, trueVal);
        executeTest(i1, voidClassifier, falseVal);

        executeTest(r1, invalid, invalid);
        executeTest(r1, nullVal, invalid);
        executeTest(r1, realClassifier, trueVal);
        executeTest(r1, intClassifier, falseVal); //reals are not integers
        executeTest(r1, voidClassifier, falseVal);

        /*
         * null values emulates the behavior of the static type, in this case, as we override getSourceType
         * method, is a real value.
         */
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, realClassifier, falseVal);
        executeTest(nullVal, intClassifier, falseVal);
        executeTest(nullVal, voidClassifier, trueVal);
    }
}
