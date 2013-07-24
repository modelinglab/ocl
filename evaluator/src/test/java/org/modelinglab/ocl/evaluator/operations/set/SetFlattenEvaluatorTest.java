/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.set.Flatten;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetFlattenEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public SetFlattenEvaluatorTest() {
        super(Flatten.createTemplateOperation().specificOperation(
                new SetType(AnyType.getInstance()), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(
                TesterTool.evaluate("Set{}", null, false),
                TesterTool.evaluate("Set{}", null, false));
        executeTest(
                TesterTool.evaluate("Set{1,2,3,4}", null, false),
                TesterTool.evaluate("Set{1,2,3,4}", null, false));
        executeTest(
                TesterTool.evaluate("Set{Bag{1}, Bag{2,3,Bag{4}}, 'a'}", null, false),
                TesterTool.evaluate("Set{1,2,3,4, 'a'}", null, false));
    }
}
