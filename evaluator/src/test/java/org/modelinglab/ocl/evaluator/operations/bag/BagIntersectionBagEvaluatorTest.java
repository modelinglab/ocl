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
import org.modelinglab.ocl.core.standard.operations.bag.IntersectionBag;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author gortiz
 */
public class BagIntersectionBagEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest {
    
    public BagIntersectionBagEvaluatorTest() throws Exception {
        super(IntersectionBag.createTemplateOperation().specificOperation(
                new BagType(realType), 
                new ArrayList<Classifier>(Collections.singletonList(new BagType(realType))),
                new TemplateRestrictions()));
    }

    @Test
    public void testVisit_BagValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("Bag{1, 2, 2, 3}", null, false);
        BagValue<?> emptyBag = new BagValue<>(Collections.<OclValue<?>>emptyList(), realType, true);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, invalid);
        executeTest(input, input, TesterTool.evaluate("Bag{1, 2, 3, 2, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Bag{2, 2, 3}", null, false), TesterTool.evaluate("Bag{2, 3, 2, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Bag{1, 2}", null, false), TesterTool.evaluate("Bag{2, 1, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Bag{10, 11, 12, 12}", null, false), emptyBag);
    }

    @Test
    public void testSpecial() throws Exception {
        OclValue<?> inputVal = TesterTool.evaluate("Bag{1, 2.0}", null, false);
        
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, inputVal, invalid);

        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, inputVal, invalid);
        
    }
}