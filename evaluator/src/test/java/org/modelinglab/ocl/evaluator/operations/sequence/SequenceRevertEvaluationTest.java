/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.sequence.Reverse;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceRevertEvaluationTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public SequenceRevertEvaluationTest() {
        super(Reverse.createTemplateOperation().specificOperation(
                new SequenceType(realType), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptySequence = new SequenceValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(emptySequence, emptySequence);
        executeTest(
                TesterTool.evaluate("Sequence{1,2,3,4}", null, false),
                TesterTool.evaluate("Sequence{4,3,2,1}", null, false));
    }
}
