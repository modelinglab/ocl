/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.operations.set;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.set.IntersectionBag;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 */
public class SetIntersectionBagEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest {
    
    public SetIntersectionBagEvaluatorTest() throws Exception {
        super(IntersectionBag.createTemplateOperation().specificOperation(
                new SetType(realType), 
                new ArrayList<Classifier>(Collections.singletonList(new BagType(realType))),
                new TemplateRestrictions()));
    }

    @Test
    public void testVisit_BagValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("Set{1, 2, 2, 3, 0.0}->excluding(0.0)", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, invalid);
        executeTest(input, TesterTool.evaluate("Bag{1, 2, 2, 3}", null, false), input);
        executeTest(input, TesterTool.evaluate("Bag{2, 2, 3}", null, false), TesterTool.evaluate("Set{2, 3, 2, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Bag{1, 2}", null, false), TesterTool.evaluate("Set{2, 1, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Bag{10, 11, 12, 12}", null, false), TesterTool.evaluate("Set{0.0}->excluding(0.0)", null, false));
    }

    @Test
    public void testSpecial() throws Exception {
        OclValue<?> argVal = TesterTool.evaluate("Bag{1, 2.0}", null, false);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, argVal, invalid);

        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, argVal, invalid);
        
    }

}
