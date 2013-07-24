/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.set.SymmetricDifference;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetSymmetricDifferenceEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest{
    
    public SetSymmetricDifferenceEvaluatorTest() {
        super(SymmetricDifference.createTemplateOperation().specificOperation(
                new SetType(realType), 
                Arrays.asList(new Classifier[] {new SetType(realType)}),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptySet = new SetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> input1 = TesterTool.evaluate("Set{1, 2.0}", null, false);
        OclValue<?> input2 = TesterTool.evaluate("Set{1, 3, 4}", null, false);
        OclValue<?> input3 = TesterTool.evaluate("Set{-1, -2}", null, false);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid); 
        executeTest(invalid, input1, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, input1, invalid);
        
        executeTest(input1, invalid, invalid);
        executeTest(input1, nullVal, invalid);
        executeTest(input1, input1, emptySet);
        executeTest(input1, input2, TesterTool.evaluate("Set{2.0, 3, 4}", null, false));
        executeTest(input1, input3, TesterTool.evaluate("Set{1, 2.0, -1, -2}", null, false));
    }
}