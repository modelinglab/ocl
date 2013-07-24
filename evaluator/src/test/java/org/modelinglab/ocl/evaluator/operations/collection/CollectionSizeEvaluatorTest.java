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
import org.modelinglab.ocl.core.standard.operations.collection.Size;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.IntegerValue;
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
public class CollectionSizeEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public CollectionSizeEvaluatorTest() {
        super(Size.createTemplateOperation().specificOperation(
                new OrderedSetType(AnyType.getInstance()), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        IntegerValue zero = new IntegerValue(0l);
        IntegerValue two = new IntegerValue(2l);
        OclValue<?> emptyBag = new BagValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> emptySet = new SetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> emptySeq = new SequenceValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> emptyOrd = new OrderedSetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        
        executeTest(emptyBag, zero);
        executeTest(emptySet, zero);
        executeTest(emptySeq, zero);
        executeTest(emptyOrd, zero);
        
        executeTest(TesterTool.evaluate("Bag{1,2}", null, false), two);
        executeTest(TesterTool.evaluate("Set{1,2}", null, false), two);
        executeTest(TesterTool.evaluate("Sequence{1,2}", null, false), two);
        executeTest(TesterTool.evaluate("OrderedSet{1,2}", null, false), two);
        
    }
}
