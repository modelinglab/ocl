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
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.bag.UnionBag;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 */
public class BagUnionBagEvaluatorTest  extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest {
    
    public BagUnionBagEvaluatorTest() throws Exception {
        super(UnionBag.createTemplateOperation().specificOperation(
                new BagType(realType), 
                new ArrayList<Classifier>(Collections.singletonList(new BagType(realType))),
                new TemplateRestrictions()));
    }

    @Test
    public void testVisit_BagValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("Bag{1, 2, 2, 3, 0.0}->excluding(0.0)", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, invalid);
        executeTest(input, TesterTool.evaluate("Bag{1, 2, 2, 3}", null, false), TesterTool.evaluate("Bag{1, 1, 2, 2, 2, 2, 3, 3, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Bag{2, 2, 3}", null, false), TesterTool.evaluate("Bag{1, 2, 2, 3, 3, 2, 2, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Bag{1, 2}", null, false), TesterTool.evaluate("Bag{2, 2, 1, 1, 3, 2, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Bag{10, 11, 12, 12}", null, false), TesterTool.evaluate("Bag{1, 2, 2, 3, 10, 11, 12, 12, 0.0}->excluding(0.0)", null, false));
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
