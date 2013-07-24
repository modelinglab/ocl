/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.set.AsOrderedSet;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetAsOrderedSetEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public SetAsOrderedSetEvaluatorTest() {
        super(AsOrderedSet.createTemplateOperation().specificOperation(
                new SetType(realType), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptySet = new SetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> normalSet = TesterTool.evaluate("Set{1,2, 3, 2}", null, false);
        
        OclValue<?> emptyOrderedSet = new OrderedSetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        /*
         * We have to play with the arbitrary order that operation evaluator uses :S
         */
        OclValue<?> resultOrderedSet = TesterTool.evaluate("OrderedSet{1, 2, 3}", null, false);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(emptySet, emptyOrderedSet);
        executeTest(normalSet, resultOrderedSet);
    }
}
