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
import org.modelinglab.ocl.core.standard.operations.sequence.AsSet;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceAsSetEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest{
    
    public SequenceAsSetEvaluatorTest() {
        super(AsSet.createTemplateOperation().specificOperation(
                new SequenceType(realType), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptySeq = new SequenceValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> withoutRepsSeq = TesterTool.evaluate("Sequence{1,2,3}", null, false);
        OclValue<?> withRepsSeq = TesterTool.evaluate("Sequence{1,2, 3, 2}", null, false);
        
        OclValue<?> emptySet = new SetValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(emptySeq, emptySet);
        executeTest(withoutRepsSeq, TesterTool.evaluate("Set{1,2,3}", null, false));
        executeTest(withRepsSeq, TesterTool.evaluate("Set{1,2,3}", null, false));
    }
}
