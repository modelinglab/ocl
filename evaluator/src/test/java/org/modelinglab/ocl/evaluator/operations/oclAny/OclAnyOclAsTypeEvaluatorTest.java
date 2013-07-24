/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.junit.Test;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.standard.operations.oclAny.OclAsType;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OclAnyOclAsTypeEvaluatorTest  {
    
    @Test
    public void test() throws Exception {
        new OclAsTypeInteger().test();
    }
    
    public static class OclAsTypeInteger extends AbstractBinaryOperationTest {

        public OclAsTypeInteger() {
            super(new OclAsType(OclAsType.createTemplateOperation(), new ClassifierType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER))));
        }

        @Override
        protected Classifier getArgType(Operation op) {
            return PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL).getClassifierType();
        }
        
        public void test() throws Exception {
            ClassifierValue intClassifier = new ClassifierValue(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
            ClassifierValue realClassifier = new ClassifierValue(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL));
            
            RealValue r1 = new RealValue(10d);
            IntegerValue i1 = new IntegerValue(10l);
            /*
             * TODO: Ocl Parser does not parse oclAsType when argument type is not a Classifier, so we
             * cannot test, using the parser, what is happening in this case.
             */
            executeTest(invalid, realClassifier, invalid);
            executeTest(invalid, intClassifier, invalid);
            
            executeTest(i1, realClassifier, i1);
            executeTest(i1, intClassifier, i1);
            
            executeTest(r1, realClassifier, r1);
            executeTest(r1, intClassifier, invalid); //reals are not integers
            
            /*
             * null values emulates the behavior of the static type, in this case, as we override getSourceType
             * method, is a real value.
             */
            executeTest(nullVal, realClassifier, nullVal);
            executeTest(nullVal, intClassifier, nullVal);
        }
        
    }
}
