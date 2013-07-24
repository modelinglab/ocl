/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import java.util.Arrays;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.set.Excluding;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetExcludingEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest {
    
    public SetExcludingEvaluatorTest() {
        super(Excluding.createTemplateOperation().specificOperation(
                new SetType(realType), 
                Arrays.asList(new Classifier[] {realType}),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        RealValue presentElement = new RealValue(2d);
        RealValue notPresentElement = new RealValue(-23d);
        
        OclValue<?> inputVal = TesterTool.evaluate("Set{1, 2.0, 3, 2, 2.0, 4.0}", null, false);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, presentElement, invalid);
        executeTest(invalid, notPresentElement, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, presentElement, invalid);
        executeTest(nullVal, notPresentElement, invalid);
        
        executeTest(inputVal, invalid, invalid);
        executeTest(inputVal, nullVal, inputVal);
        executeTest(inputVal, presentElement, TesterTool.evaluate("Set{1, 3, 2, 4.0}", null, false));
        executeTest(inputVal, notPresentElement, inputVal);
    }
}
