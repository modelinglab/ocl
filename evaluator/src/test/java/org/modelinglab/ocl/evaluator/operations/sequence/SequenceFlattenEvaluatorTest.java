/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.sequence.Flatten;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceFlattenEvaluatorTest extends AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest {
    
    public SequenceFlattenEvaluatorTest() {
        super(Flatten.createTemplateOperation().specificOperation(
                new SequenceType(AnyType.getInstance()), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(
                TesterTool.evaluate("Sequence{}", null, false),
                TesterTool.evaluate("Sequence{}", null, false));
        executeTest(
                TesterTool.evaluate("Sequence{1,2,3,4}", null, false),
                TesterTool.evaluate("Sequence{1,2,3,4}", null, false));
        executeTest(
                TesterTool.evaluate("Sequence{Bag{1}, Bag{2,3,Bag{4}}, 'a'}", null, false),
                TesterTool.evaluate("Sequence{1,2,3,4, 'a'}", null, false));
    }
}
