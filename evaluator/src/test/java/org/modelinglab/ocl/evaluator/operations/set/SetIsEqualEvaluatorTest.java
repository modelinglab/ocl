/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import java.util.Arrays;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.set.IsEqual;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetIsEqualEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {
    
    public SetIsEqualEvaluatorTest() {
        super(IsEqual.createTemplateOperation().specificOperation(
                new SetType(realType), 
                Arrays.asList(new Classifier[] {new SetType(realType)}),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> inputVal = TesterTool.evaluate("Set{1, 2.0}", null, false);
        
//        executeTest(invalid, invalid, invalid); //it should use VoidType::=(n:VoidType):Boolean
        executeTest(invalid, nullVal, invalid); 
        executeTest(invalid, inputVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
//        executeTest(nullVal, nullVal, trueVal); //it should use VoidType::=(n:VoidType):Boolean
        executeTest(nullVal, inputVal, falseVal);
        
        executeTest(inputVal, invalid, invalid);
        executeTest(inputVal, nullVal, falseVal);
        executeTest(inputVal, inputVal, trueVal);
        executeTest(inputVal, TesterTool.evaluate("Set{2.0, 1}", null, false), trueVal);
        executeTest(inputVal, TesterTool.evaluate("Set{1}", null, false), falseVal);
    }
}
