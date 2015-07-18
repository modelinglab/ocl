/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.operations.orderedSet;

import java.util.Arrays;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.orderedSet.Prepend;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Miguel Angel Garcia de Dios <miguelangel.garcia at imdea.org>
 */
public class OrderedSetPrependEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest {
    
    public OrderedSetPrependEvaluatorTest() {
        super(Prepend.createTemplateOperation().specificOperation(
            new OrderedSetType(realType), 
            Arrays.asList(new Classifier[] {realType}),
            new TemplateRestrictions()));
    }
    
    @Test
    public void test() throws Exception {
        RealValue presentElement = new RealValue(2d);
        RealValue notPresentElement = new RealValue(-23d);
        
        OclValue<?> inputVal = TesterTool.evaluate("OrderedSet{1, 2, 2.0, 3}", null, false);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, presentElement, invalid);
        executeTest(invalid, notPresentElement, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, presentElement, invalid);
        executeTest(nullVal, notPresentElement, invalid);
        
        executeTest(inputVal, invalid, invalid);
        executeTest(inputVal, nullVal, TesterTool.evaluate("OrderedSet{null, 1, 2, 2.0, 3}", null, false));
        executeTest(inputVal, presentElement, TesterTool.evaluate("OrderedSet{2.0, 1, 2, 3}", null, false));
        executeTest(inputVal, notPresentElement, TesterTool.evaluate("OrderedSet{-23.0, 1, 2, 2.0, 3}", null, false));
        
    }
}
