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
import org.modelinglab.ocl.core.standard.operations.set.Substraction;
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
public class SetSubstractionEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {
    
    public SetSubstractionEvaluatorTest() {
        super(Substraction.createTemplateOperation().specificOperation(
                new SetType(realType), 
                Arrays.asList(new Classifier[] {new SetType(realType)}),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptySet = new SetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> inputVal = TesterTool.evaluate("Set{1, 2.0}", null, false);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid); 
        executeTest(invalid, inputVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, inputVal, invalid);
        
        executeTest(inputVal, invalid, invalid);
        executeTest(inputVal, nullVal, inputVal); //gortiz: this is not very consistent, it seems like a bug in the specification.
        executeTest(inputVal, inputVal, emptySet);
        executeTest(inputVal, 
                TesterTool.evaluate("Set{2.0, 1}", null, false), 
                emptySet);
        executeTest(inputVal, 
                TesterTool.evaluate("Set{1}", null, false), 
                TesterTool.evaluate("Set{2.0}", null, false));
        executeTest(inputVal, 
                emptySet, 
                inputVal);
        executeTest(inputVal, 
                TesterTool.evaluate("Set{-123}", null, false), 
                inputVal);
    }
}
