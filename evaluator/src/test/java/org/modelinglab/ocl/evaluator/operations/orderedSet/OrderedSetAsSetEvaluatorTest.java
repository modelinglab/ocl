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
import org.modelinglab.ocl.core.standard.operations.orderedSet.AsSet;
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
public class OrderedSetAsSetEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public OrderedSetAsSetEvaluatorTest() {
        super(AsSet.createTemplateOperation().specificOperation(
                new OrderedSetType(realType), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptyOrd = new OrderedSetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> normalOrd = TesterTool.evaluate("OrderedSet{1,2,3}", null, false);
        
        OclValue<?> emptySet = new SetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(emptyOrd, emptySet);
        executeTest(normalOrd, TesterTool.evaluate("Set{1,2,3}", null, false));
    }
}
