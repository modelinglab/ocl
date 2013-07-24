/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import java.util.Arrays;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.orderedSet.SubOrderedSet;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractTernaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetSubOrderedSetEvaluatorTest extends AbstractTernaryOperationTest.AbstractCollectionTernaryOperationTest {
    
    public OrderedSetSubOrderedSetEvaluatorTest() {
        super(SubOrderedSet.createTemplateOperation().specificOperation(
                new OrderedSetType(realType), 
                Arrays.asList(new Classifier[] {intType}),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        
        IntegerValue zero = new IntegerValue(0l);
        IntegerValue two = new IntegerValue(2l);
        IntegerValue four = new IntegerValue(4l);
        IntegerValue ten = new IntegerValue(10l);
        
        OclValue<?> input = TesterTool.evaluate("OrderedSet{1,2,3,4,5,6,7}", null, false);
        
        executeTest(invalid, two, ten, invalid);
        executeTest(nullVal, two, ten, invalid);
        
        executeTest(input, zero, four, invalid);
        executeTest(input, four, two, invalid);
        executeTest(input, two, ten, invalid);
        
        executeTest(input, two, four, TesterTool.evaluate("OrderedSet{2,3,4}", null, false));
        executeTest(input, two, two, TesterTool.evaluate("OrderedSet{2}", null, false));
        executeTest(input, four, four, TesterTool.evaluate("OrderedSet{4}", null, false));
        
    }
}
