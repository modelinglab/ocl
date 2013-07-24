/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import java.util.Arrays;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.bag.IsEqual;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagIsEqualEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {
    
    public BagIsEqualEvaluatorTest() {
        super(IsEqual.createTemplateOperation().specificOperation(
                new BagType(realType), 
                Arrays.asList(new Classifier[] {new BagType(realType)}),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> inputVal = TesterTool.evaluate("Bag{1, 2.0}", null, false);
        
//        executeTest(invalid, invalid, invalid); //it should use VoidType::=(n:VoidType):Boolean
        executeTest(invalid, nullVal, invalid); 
        executeTest(invalid, inputVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
//        executeTest(nullVal, nullVal, trueVal); //it should use VoidType::=(n:VoidType):Boolean
        executeTest(nullVal, inputVal, falseVal);
        
        executeTest(inputVal, invalid, invalid);
        executeTest(inputVal, nullVal, falseVal);
        executeTest(inputVal, inputVal, trueVal);
        executeTest(inputVal, TesterTool.evaluate("Bag{2.0, 1}", null, false), trueVal);
        executeTest(inputVal, TesterTool.evaluate("Bag{1}", null, false), falseVal);
        
        
    }
}
