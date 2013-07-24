/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.operations.bag;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.bag.UnionSet;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 */
public class BagUnionSetEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest {
    
    public BagUnionSetEvaluatorTest() throws Exception {
        super(UnionSet.createTemplateOperation().specificOperation(
                new BagType(realType), 
                new ArrayList<Classifier>(Collections.singletonList(new SetType(realType))),
                new TemplateRestrictions()));
    }

    @Test
    public void testVisit_SetValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("Bag{1, 2, 2, 3, 0.0}->excluding(0.0)", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, invalid);
        executeTest(input, TesterTool.evaluate("Set{1, 2, 2, 3}", null, false), TesterTool.evaluate("Bag{1, 1, 2, 2, 2, 3, 3, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Set{2, 2, 3}", null, false), TesterTool.evaluate("Bag{1, 2, 2, 2, 3, 3, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Set{1, 2}", null, false), TesterTool.evaluate("Bag{2, 1, 1, 2, 2, 3, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Set{10, 11, 12, 12}", null, false), TesterTool.evaluate("Bag{1, 2, 2, 3, 10, 11, 12, 0.0}->excluding(0.0)", null, false));
    }

    @Test
    public void testSpecial() throws Exception {
        OclValue<?> argVal = TesterTool.evaluate("Set{1, 2.0}", null, false);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, argVal, invalid);

        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, argVal, invalid);
        
    }

}
