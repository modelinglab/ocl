/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.bag.AsSequence;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagAsSequenceEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public BagAsSequenceEvaluatorTest() {
        super(AsSequence.createTemplateOperation().specificOperation(
                new BagType(realType), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        OclValue<?> emptyBag = new BagValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> withoutRepsBag = TesterTool.evaluate("Bag{1,2,3}", null, false);
        OclValue<?> withRepsBag = TesterTool.evaluate("Bag{1,2, 3, 2}", null, false);
        
        OclValue<?> emptySequence = new SequenceValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(emptyBag, emptySequence);
        executeTest(withoutRepsBag, TesterTool.evaluate("Sequence{1,2,3}", null, false));
        executeTest(withRepsBag, TesterTool.evaluate("Sequence{1,2,3,2}", null, false));
    }
}
