/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.standard.operations.sequence.InsertAt;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractTernaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceInsertAtEvaluatorTest extends AbstractTernaryOperationTest.AbstractCollectionTernaryOperationTest {
    
    public SequenceInsertAtEvaluatorTest() {
        super(InsertAt.createTemplateOperation().specificOperation(
                new SequenceType(realType), 
                Arrays.asList(new Classifier[] {intType, realType}), 
                null));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptySeq = new SequenceValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> normalSeq = TesterTool.evaluate("Sequence{1.0,2,3,4}", null, false);
        
        IntegerValue negative = new IntegerValue(-3l);
        IntegerValue zero = new IntegerValue(0l);
        IntegerValue one = new IntegerValue(1l);
        IntegerValue last = new IntegerValue(4l);
        IntegerValue lastPlusOne = new IntegerValue(5l);
        IntegerValue two = new IntegerValue(2l);
        
        RealValue newValue = new RealValue(-1d);
        IntegerValue repeatedValue = new IntegerValue(2l);
        
        executeTest(invalid, two, newValue, invalid);
        executeTest(nullVal, two, newValue, invalid);
        
        executeTest(emptySeq, zero, newValue, invalid);
        executeTest(emptySeq, one, newValue, invalid);
        
        executeTest(normalSeq, negative, newValue, invalid);
        executeTest(normalSeq, zero, newValue, invalid);
        executeTest(normalSeq, one, newValue, TesterTool.evaluate("Sequence{-1.0, 1.0,2,3,4}", null, false));
        executeTest(normalSeq, two, newValue, TesterTool.evaluate("Sequence{1.0,-1.0, 2,3,4}", null, false));
        executeTest(normalSeq, last, newValue, TesterTool.evaluate("Sequence{1.0,2,3,-1.0,4}", null, false));
        executeTest(normalSeq, lastPlusOne, newValue, invalid);
        
        executeTest(normalSeq, negative, repeatedValue, invalid);
        executeTest(normalSeq, zero, repeatedValue, invalid);
        executeTest(normalSeq, one, repeatedValue, TesterTool.evaluate("Sequence{2, 1.0,2,3,4}", null, false));
        executeTest(normalSeq, two, repeatedValue, TesterTool.evaluate("Sequence{1.0,2, 2,3,4}", null, false));
        executeTest(normalSeq, last, repeatedValue, TesterTool.evaluate("Sequence{1.0,2,3,2,4}", null, false));
        executeTest(normalSeq, lastPlusOne, repeatedValue, invalid);
        
    }
}
