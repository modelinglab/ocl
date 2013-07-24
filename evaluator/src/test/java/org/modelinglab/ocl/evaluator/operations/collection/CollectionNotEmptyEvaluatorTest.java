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
import org.modelinglab.ocl.core.standard.operations.collection.NotEmpty;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionNotEmptyEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public CollectionNotEmptyEvaluatorTest() {
        super(NotEmpty.createTemplateOperation().specificOperation(
                new OrderedSetType(AnyType.getInstance()), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        
        OclValue<?> emptyBag = new BagValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> emptySet = new SetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> emptySeq = new SequenceValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> emptyOrd = new OrderedSetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        
        executeTest(emptyBag, falseVal);
        executeTest(emptySet, falseVal);
        executeTest(emptySeq, falseVal);
        executeTest(emptyOrd, falseVal);
        
        executeTest(TesterTool.evaluate("Bag{1,2}", null, false), trueVal);
        executeTest(TesterTool.evaluate("Set{1,2}", null, false), trueVal);
        executeTest(TesterTool.evaluate("Sequence{1,2}", null, false), trueVal);
        executeTest(TesterTool.evaluate("OrderedSet{1,2}", null, false), trueVal);
        
    }
}
