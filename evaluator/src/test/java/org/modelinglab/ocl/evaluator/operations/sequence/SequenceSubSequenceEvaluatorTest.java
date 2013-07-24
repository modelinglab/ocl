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
import org.modelinglab.ocl.core.standard.operations.sequence.SubSequence;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractTernaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceSubSequenceEvaluatorTest extends AbstractTernaryOperationTest.AbstractCollectionTernaryOperationTest {
    
    public SequenceSubSequenceEvaluatorTest() {
        super(SubSequence.createTemplateOperation().specificOperation(
                new SequenceType(realType), 
                Arrays.asList(new Classifier[] {intType}),
                new TemplateRestrictions()));
    }

    @Test
    public void test() throws Exception {
        
        IntegerValue zero = new IntegerValue(0l);
        IntegerValue two = new IntegerValue(2l);
        IntegerValue four = new IntegerValue(4l);
        IntegerValue ten = new IntegerValue(10l);
        
        OclValue<?> input = TesterTool.evaluate("Sequence{1,2,3,4,5,6,7}", null, false);
        
        executeTest(invalid, two, ten, invalid);
        executeTest(nullVal, two, ten, invalid);
        
        executeTest(input, zero, four, invalid);
        executeTest(input, four, two, invalid);
        executeTest(input, two, ten, invalid);
        
        executeTest(input, two, four, TesterTool.evaluate("Sequence{2,3,4}", null, false));
        executeTest(input, two, two, TesterTool.evaluate("Sequence{2}", null, false));
        executeTest(input, four, four, TesterTool.evaluate("Sequence{4}", null, false));
        
    }
}
