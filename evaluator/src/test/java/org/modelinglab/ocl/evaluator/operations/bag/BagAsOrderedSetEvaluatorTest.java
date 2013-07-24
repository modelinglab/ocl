/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.ast.utils.OrderedSet;
import org.modelinglab.ocl.core.standard.operations.bag.AsOrderedSet;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagAsOrderedSetEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public BagAsOrderedSetEvaluatorTest() {
        super(AsOrderedSet.createTemplateOperation().specificOperation(
                new BagType(realType), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptyBag = new BagValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> withoutRepsBag = TesterTool.evaluate("Bag{1,2,3}", null, false);
        OclValue<?> withRepsBag = TesterTool.evaluate("Bag{1,2, 3, 2}", null, false);
        
        OclValue<?> emptyOrderedSet = new OrderedSetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        /*
         * We have to play with the arbitrary order that operation evaluator uses :S
         */
        OclValue<?> resultOrderedSet = TesterTool.evaluate("OrderedSet{3,2,1}", null, false);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(emptyBag, emptyOrderedSet);
        executeTest(withoutRepsBag, resultOrderedSet);
        executeTest(withRepsBag, resultOrderedSet);
        
    }
}

