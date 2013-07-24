/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import java.util.Arrays;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.collection.Includes;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionIncludesEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest {
    
    RealValue includedVal = new RealValue(0d);
    RealValue excludedVal = new RealValue(1d);
    
    public CollectionIncludesEvaluatorTest() {
        super(Includes.createTemplateOperation().specificOperation(
                new SetType(realType), 
                Arrays.asList(new Classifier[] {realType}),
                new TemplateRestrictions()));
    }
    
    @Test
    public void testVisit_SpecialCases() throws Exception {
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, includedVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, includedVal, invalid);
    }

    @Test
    public void testVisit_BagValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("Bag{1, 2, 0.0, 3}", null, false);
        OclValue<?> inputWithNull = TesterTool.evaluate("Bag{null, 1, 2, 0.0, 3}", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, falseVal);
        executeTest(inputWithNull, nullVal, trueVal);
        executeTest(input, includedVal, trueVal);
        executeTest(input, excludedVal, falseVal);
    }

    @Test
    public void testVisit_OrderedSetValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("OrderedSet{1, 2, 0.0, 3}", null, false);
        OclValue<?> inputWithNull = TesterTool.evaluate("OrderedSet{null, 1, 2, 0.0, 3}", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, falseVal);
        executeTest(inputWithNull, nullVal, trueVal);
        executeTest(input, includedVal, trueVal);
        executeTest(input, excludedVal, falseVal);
    }

    @Test
    public void testVisit_SequenceValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("Sequence{1, 2, 0.0, 3}", null, false);
        OclValue<?> inputWithNull = TesterTool.evaluate("Sequence{null, 1, 2, 0.0, 3}", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, falseVal);
        executeTest(inputWithNull, nullVal, trueVal);
        executeTest(input, includedVal, trueVal);
        executeTest(input, excludedVal, falseVal);
    }

    @Test
    public void testVisit_SetValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("Set{1, 2, 0.0, 3}", null, false);
        OclValue<?> inputWithNull = TesterTool.evaluate("Set{null, 1, 2, 0.0, 3}", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, falseVal);
        executeTest(inputWithNull, nullVal, trueVal);
        executeTest(input, includedVal, trueVal);
        executeTest(input, excludedVal, falseVal);
    }
}
