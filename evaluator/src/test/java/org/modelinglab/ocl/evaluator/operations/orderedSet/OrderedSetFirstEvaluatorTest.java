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
import org.modelinglab.ocl.core.standard.operations.orderedSet.First;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetFirstEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest{
    
    public OrderedSetFirstEvaluatorTest() {
        super(First.createTemplateOperation().specificOperation(
                new OrderedSetType(realType), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptyOrderedSet = new OrderedSetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(emptyOrderedSet, invalid);
        executeTest(TesterTool.evaluate("OrderedSet{1,2,3}", null, false), new IntegerValue(1l));
    }
}
