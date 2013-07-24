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
import org.modelinglab.ocl.core.standard.operations.bag.AsBag;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest.AbstractCollectionUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagAsBagEvaluatorTest extends AbstractCollectionUnaryOperationTest {
    
    public BagAsBagEvaluatorTest() {
        super(AsBag.createTemplateOperation().specificOperation(
                new BagType(realType), 
                Collections.<Classifier>emptyList(),
                new TemplateRestrictions()));
    }

    @Test
    public void testSomeMethod() throws Exception {
        OclValue<?> emptyBag = new BagValue<>(Collections.<BooleanValue>emptyList(), realType, true);
        OclValue<?> notEmptyBag = TesterTool.evaluate("Bag{1,2,3}", null, false);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(emptyBag, emptyBag);
        executeTest(notEmptyBag, notEmptyBag);
    }
}
