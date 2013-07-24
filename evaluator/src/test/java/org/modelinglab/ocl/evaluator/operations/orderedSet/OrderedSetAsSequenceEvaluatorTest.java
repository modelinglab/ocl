/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.orderedSet.AsSequence;
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
public class OrderedSetAsSequenceEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public OrderedSetAsSequenceEvaluatorTest() {
        super(AsSequence.createTemplateOperation().specificOperation(
                new OrderedSetType(realType), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptyOrderedSet = new OrderedSetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> withoutRepsOrderedSet = TesterTool.evaluate("OrderedSet{1,2,3}", null, false);
        OclValue<?> withRepsOrderedSet = TesterTool.evaluate("OrderedSet{1,2, 3, 2}", null, false);
        
        OclValue<?> emptySequence = new SequenceValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(emptyOrderedSet, emptySequence);
        executeTest(withoutRepsOrderedSet, TesterTool.evaluate("Sequence{1,2,3}", null, false));
        executeTest(withRepsOrderedSet, TesterTool.evaluate("Sequence{1,2,3}", null, false));
    }
}
