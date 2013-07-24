/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.sequence.AsOrderedSet;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceAsOrderedSetEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public SequenceAsOrderedSetEvaluatorTest() {
        super(AsOrderedSet.createTemplateOperation().specificOperation(
                new SequenceType(realType), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptyOrderedSet = new OrderedSetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> emptySequence = new SequenceValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> withoutReps = TesterTool.evaluate("Sequence{1,2,3}", null, false);
        OclValue<?> withReps = TesterTool.evaluate("Sequence{1,2, 3, 2}", null, false);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(emptySequence, emptyOrderedSet);
        executeTest(withoutReps, TesterTool.evaluate("OrderedSet{1,2,3}", null, false));
        executeTest(withReps, TesterTool.evaluate("OrderedSet{1,2,3,2}", null, false));
    }
}
