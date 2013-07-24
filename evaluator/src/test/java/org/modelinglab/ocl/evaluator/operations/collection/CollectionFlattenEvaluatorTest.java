/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.collection.Flatten;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionFlattenEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public CollectionFlattenEvaluatorTest() {
        super(Flatten.createTemplateOperation().specificOperation(
                new OrderedSetType(AnyType.getInstance()), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(
                TesterTool.evaluate("OrderedSet{}", null, false),
                TesterTool.evaluate("OrderedSet{}", null, false));
        executeTest(
                TesterTool.evaluate("OrderedSet{1,2,3,4}", null, false),
                TesterTool.evaluate("OrderedSet{1,2,3,4}", null, false));
        executeTest(
                TesterTool.evaluate("OrderedSet{Bag{1}, Bag{2,3,Bag{4}}, 'a'}", null, false),
                TesterTool.evaluate("Sequence{1,2,3,4, 'a'}", null, false));
    }
}
