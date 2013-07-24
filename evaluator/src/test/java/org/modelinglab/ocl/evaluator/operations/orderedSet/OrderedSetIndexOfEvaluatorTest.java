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
import org.modelinglab.ocl.core.standard.operations.orderedSet.IndexOf;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import org.modelinglab.ocl.evaluator.operations.AbstractTernaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetIndexOfEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest {
    
    public OrderedSetIndexOfEvaluatorTest() {
        super(IndexOf.createTemplateOperation().specificOperation(
                new OrderedSetType(realType), 
                Arrays.asList(new Classifier[] {intType}),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        IntegerValue zero = new IntegerValue(0l);
        IntegerValue two = new IntegerValue(2l);
        IntegerValue ten = new IntegerValue(10l);
        
        OclValue<?> input = TesterTool.evaluate("OrderedSet{1, 10, 3, 4}", null, false);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, zero, invalid);
        executeTest(invalid, two, invalid);
        executeTest(invalid, ten, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, zero, invalid);
        executeTest(nullVal, two, invalid);
        executeTest(nullVal, ten, invalid);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, invalid);
        executeTest(input, zero, invalid);
        executeTest(input, two, invalid);
        executeTest(input, ten, two);
        
        
    }
}
