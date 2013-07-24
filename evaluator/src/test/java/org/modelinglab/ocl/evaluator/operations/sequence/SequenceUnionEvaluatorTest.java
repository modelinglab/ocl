/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.sequence.Union;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceUnionEvaluatorTest extends AbstractBinaryOperationTest.AbstractCollectionBinaryOperationTest{
    
    public SequenceUnionEvaluatorTest() {
        super(Union.createTemplateOperation().specificOperation(
                new SequenceType(realType), 
                Arrays.asList(new Classifier[] {new SequenceType(realType)}),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptySeq = new SequenceValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> inputVal = TesterTool.evaluate("Sequence{1, 2.0}", null, false);
        
        executeTest(invalid, invalid, invalid);
        executeTest(invalid, nullVal, invalid); 
        executeTest(invalid, inputVal, invalid);
        
        executeTest(nullVal, invalid, invalid);
        executeTest(nullVal, nullVal, invalid);
        executeTest(nullVal, inputVal, invalid);
        
        executeTest(emptySeq, invalid, invalid);
        executeTest(emptySeq, nullVal, invalid);
        executeTest(emptySeq, emptySeq, emptySeq);
        executeTest(emptySeq, inputVal, inputVal);
        
        executeTest(inputVal, invalid, invalid);
        executeTest(inputVal, nullVal, invalid);
        executeTest(inputVal, inputVal, TesterTool.evaluate("Sequence{1,2.0, 1, 2.0}", null, false));
        executeTest(inputVal, emptySeq, inputVal);
    }
}
