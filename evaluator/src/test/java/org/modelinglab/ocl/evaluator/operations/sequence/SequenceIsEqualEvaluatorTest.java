/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.Arrays;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.sequence.IsEqual;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceIsEqualEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {

    public SequenceIsEqualEvaluatorTest() {
        super(IsEqual.createTemplateOperation().specificOperation(
                new SequenceType(realType), 
                Arrays.asList(new Classifier[] {new SequenceType(realType)}),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> inputVal = TesterTool.evaluate("Sequence{1, 2.0}", null, false);
        
//        executeTest(invalid, invalid, invalid); //it should use VoidType::=(n:VoidType):Boolean
        executeTest(invalid, nullVal, invalid); 
        executeTest(invalid, inputVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
//        executeTest(nullVal, nullVal, trueVal); //it should use VoidType::=(n:VoidType):Boolean
        executeTest(nullVal, inputVal, falseVal);
        
        executeTest(inputVal, invalid, invalid);
        executeTest(inputVal, nullVal, falseVal);
        executeTest(inputVal, inputVal, trueVal);
        executeTest(inputVal, TesterTool.evaluate("Sequence{2.0, 1}", null, false), falseVal);
        executeTest(inputVal, TesterTool.evaluate("Sequence{1}", null, false), falseVal);
        
        
    }
}
