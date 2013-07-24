/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import java.util.Arrays;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.collection.IncludesAll;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator.SwitchArgument;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionIncludesAllEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest{
    
    OclValue<?> includedVal;
    OclValue<?> mixedVal;
    OclValue<?> excludedVal;
    
    public CollectionIncludesAllEvaluatorTest() throws Exception {
        super(IncludesAll.createTemplateOperation().specificOperation(
                new SetType(realType), 
                Arrays.asList(new Classifier[] {realType}),
                new TemplateRestrictions()));
        
        includedVal = TesterTool.evaluate("Set{1, 2}", null, false);
        mixedVal = TesterTool.evaluate("Set{0.0, -12}", null, false);
        excludedVal = TesterTool.evaluate("Set{-12}", null, false);
    }

    @Test
    public void testVisit_BagValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("Bag{1, 2, 0.0, 3}", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, invalid);
        executeTest(input, includedVal, trueVal);
        executeTest(input, mixedVal, falseVal);
        executeTest(input, excludedVal, falseVal);
    }

    @Test
    public void testVisit_OrderedSetValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("OrderedSet{1, 2, 0.0, 3}", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, invalid);
        executeTest(input, includedVal, trueVal);
        executeTest(input, mixedVal, falseVal);
        executeTest(input, excludedVal, falseVal);
    }

    @Test
    public void testVisit_SequenceValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("Sequence{1, 2, 0.0, 3}", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, invalid);
        executeTest(input, includedVal, trueVal);
        executeTest(input, mixedVal, falseVal);
        executeTest(input, excludedVal, falseVal);
    }

    @Test
    public void testVisit_SetValue() throws Exception {
        OclValue<?> input = TesterTool.evaluate("Set{1, 2, 0.0, 3}", null, false);
        
        executeTest(input, invalid, invalid);
        executeTest(input, nullVal, invalid);
        executeTest(input, includedVal, trueVal);
        executeTest(input, mixedVal, falseVal);
        executeTest(input, excludedVal, falseVal);
    }
}
