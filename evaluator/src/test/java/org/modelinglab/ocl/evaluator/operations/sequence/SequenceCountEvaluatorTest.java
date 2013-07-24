/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.Arrays;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.sequence.Count;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceCountEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest{
    
    public SequenceCountEvaluatorTest() {
        super(Count.createTemplateOperation().specificOperation(
                new SequenceType(realType), 
                Arrays.asList(new Classifier[] {realType}),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        RealValue presentElement = new RealValue(2d);
        RealValue notPresentElement = new RealValue(-23d);
        
        OclValue<?> inputVal = TesterTool.evaluate("Sequence{1, 2.0, 3, 2, 2.0, 4.0}", null, false);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, presentElement, invalid);
        executeTest(invalid, notPresentElement, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, presentElement, invalid);
        executeTest(nullVal, notPresentElement, invalid);
        
        executeTest(inputVal, invalid, invalid);
        executeTest(inputVal, nullVal, new IntegerValue(0l));
        executeTest(inputVal, presentElement, new IntegerValue(2l));
        executeTest(inputVal, notPresentElement, new IntegerValue(0l));
    }
}
