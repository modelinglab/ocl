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
import org.modelinglab.ocl.core.standard.operations.set.UnionBag;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author gortiz
 */
public class SetUnionBagEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest {
    
    public SetUnionBagEvaluatorTest() throws Exception {
        super(UnionBag.createTemplateOperation().specificOperation(
                new SetType(realType), 
                new ArrayList<Classifier>(Collections.singletonList(new BagType(realType))),
                new TemplateRestrictions()));
    }

    @Test
    public void testVisit_BagValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("Set{1, 2, 2, 3, 0.0}->excluding(0.0)", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, invalid);
        executeTest(input, TesterTool.evaluate("Bag{1, 2, 2, 3}", null, false), TesterTool.evaluate("Bag{1, 1, 2, 2, 2, 3, 3, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Bag{2, 2, 3}", null, false), TesterTool.evaluate("Bag{1, 2, 2, 3, 3, 2, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Bag{1, 2}", null, false), TesterTool.evaluate("Bag{2, 2, 1, 1, 3, 0.0}->excluding(0.0)", null, false));
        executeTest(input, TesterTool.evaluate("Bag{10, 11, 12, 12}", null, false), TesterTool.evaluate("Bag{1, 2, 3, 10, 11, 12, 12, 0.0}->excluding(0.0)", null, false));
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