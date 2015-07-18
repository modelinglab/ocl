/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.operations.orderedSet;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.standard.operations.orderedSet.InsertAt;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractTernaryOperationTest;

/**
 *
 * @author Miguel Angel Garcia de Dios <miguelangel.garcia at imdea.org>
 */
public class OrderedSetInsertAtEvaluatorTest extends AbstractTernaryOperationTest.AbstractCollectionTernaryOperationTest {
    
    public OrderedSetInsertAtEvaluatorTest() {
        super(InsertAt.createTemplateOperation().specificOperation(
            new OrderedSetType(realType), 
            Arrays.asList(new Classifier[] {intType, realType}), 
            null));
    }
    
    @Test
    public void test() throws Exception {
        OclValue<?> emptyOrdSet = new OrderedSetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> normalOrdSet = TesterTool.evaluate("OrderedSet{1.0,2,3,4}", null, false);
        
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
        
        executeTest(emptyOrdSet, zero, newValue, invalid);
        executeTest(emptyOrdSet, one, newValue, invalid);
        
        executeTest(normalOrdSet, negative, newValue, invalid);
        executeTest(normalOrdSet, zero, newValue, invalid);
        executeTest(normalOrdSet, one, newValue, TesterTool.evaluate("OrderedSet{-1.0, 1.0,2,3,4}", null, false));
        executeTest(normalOrdSet, two, newValue, TesterTool.evaluate("OrderedSet{1.0,-1.0, 2,3,4}", null, false));
        executeTest(normalOrdSet, last, newValue, TesterTool.evaluate("OrderedSet{1.0,2,3,-1.0,4}", null, false));
        executeTest(normalOrdSet, lastPlusOne, newValue, invalid);
        
        executeTest(normalOrdSet, negative, repeatedValue, invalid);
        executeTest(normalOrdSet, zero, repeatedValue, invalid);
        executeTest(normalOrdSet, one, repeatedValue, TesterTool.evaluate("OrderedSet{2, 1.0, 3, 4}", null, false));
        executeTest(normalOrdSet, two, repeatedValue, TesterTool.evaluate("OrderedSet{1.0, 2, 3, 4}", null, false));
        executeTest(normalOrdSet, last, repeatedValue, TesterTool.evaluate("OrderedSet{1.0, 3, 4, 2}", null, false));
        executeTest(normalOrdSet, lastPlusOne, repeatedValue, invalid);
        
    }
    
}
