/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.bag.Flatten;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagFlattenEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest{
    
    public BagFlattenEvaluatorTest() {
        super(Flatten.createTemplateOperation().specificOperation(
                new BagType(AnyType.getInstance()), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(
                TesterTool.evaluate("Bag{}", null, false),
                TesterTool.evaluate("Bag{}", null, false));
        executeTest(
                TesterTool.evaluate("Bag{1,2,3,4}", null, false),
                TesterTool.evaluate("Bag{1,2,3,4}", null, false));
        executeTest(
                TesterTool.evaluate("Bag{Bag{1}, Bag{2,3,Bag{4}}, 'a'}", null, false),
                TesterTool.evaluate("Bag{1,2,3,4, 'a'}", null, false));
    }
}
