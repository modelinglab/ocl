/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import java.util.Arrays;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.collection.IsDifferent;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.TesterTool;
import org.modelinglab.ocl.evaluator.operations.AbstractBinaryOperationTest;
import org.modelinglab.ocl.evaluator.operations.NotOverridenOperationException;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionIsDifferentEvaluatorTest extends AbstractBinaryOperationTest.AbstractInfixOperationTest {
    
    public CollectionIsDifferentEvaluatorTest() {
        super(IsDifferent.createTemplateOperation().specificOperation(
                new CollectionType(realType),
                Arrays.asList(new Classifier[]{new CollectionType(realType)}),
                new TemplateRestrictions()));
    }

    @Test
    public void testSpecial() throws Exception {
        OclValue<?> inputVal = TesterTool.evaluate("OrderedSet{1, 2.0}", null, false);
//        executeTest(invalid, invalid, invalid); //it should use VoidType::=(n:VoidType):Boolean
        executeTest(invalid, nullVal, invalid);
        executeTest(invalid, inputVal, invalid);

        executeTest(nullVal, invalid, invalid);
//        executeTest(nullVal, nullVal, falseVal); //it should use VoidType::=(n:VoidType):Boolean
        executeTest(nullVal, inputVal, trueVal);
        
    }
    
    @Test
    public void testOrderedSet() throws Exception {
        OclValue<?> inputVal = TesterTool.evaluate("OrderedSet{1, 2.0}", null, false);

        executeTest(inputVal, invalid, invalid);
        executeTest(inputVal, nullVal, trueVal);
        executeTest(inputVal, inputVal, falseVal);
        executeTest(inputVal, TesterTool.evaluate("OrderedSet{2.0, 1}", null, false), trueVal);

        executeTest(inputVal, TesterTool.evaluate("Set{1, 2.0}", null, false), trueVal);
        executeTest(inputVal, TesterTool.evaluate("OrderedSet{1, 2.0}", null, false), falseVal);
        executeTest(inputVal, TesterTool.evaluate("Bag{1, 2.0}", null, false), trueVal);
        executeTest(inputVal, TesterTool.evaluate("Sequence{1, 2.0}", null, false), trueVal);
    }

    @Test
    public void testSet() throws Exception {
        OclValue<?> inputVal = TesterTool.evaluate("Set{1, 2.0}", null, false);

        try {
            executeTest(inputVal, invalid, invalid);
            assert false;
        } catch (NotOverridenOperationException ex) {
        }
        try {
            executeTest(inputVal, nullVal, trueVal);
            assert false;
        } catch (NotOverridenOperationException ex) {
        }
        try {
            executeTest(inputVal, TesterTool.evaluate("Set{1, 2.0}", null, false), falseVal);
            assert false;
        } catch (NotOverridenOperationException ex) {
        }
        executeTest(inputVal, TesterTool.evaluate("OrderedSet{1, 2.0}", null, false), trueVal);
        executeTest(inputVal, TesterTool.evaluate("Bag{1, 2.0}", null, false), trueVal);
        executeTest(inputVal, TesterTool.evaluate("Sequence{1, 2.0}", null, false), trueVal);
    }

    @Test
    public void testSequence() throws Exception {
        OclValue<?> inputVal = TesterTool.evaluate("Sequence{1, 2.0}", null, false);

        try {
            executeTest(inputVal, invalid, invalid);
            assert false;
        } catch (NotOverridenOperationException ex) {
        }
        try {
            executeTest(inputVal, nullVal, trueVal);
            assert false;
        } catch (NotOverridenOperationException ex) {
        }
        executeTest(inputVal, TesterTool.evaluate("Set{1, 2.0}", null, false), trueVal);
        executeTest(inputVal, TesterTool.evaluate("OrderedSet{1, 2.0}", null, false), trueVal);
        executeTest(inputVal, TesterTool.evaluate("Bag{1, 2.0}", null, false), trueVal);
        try {
            executeTest(inputVal, TesterTool.evaluate("Sequence{1, 2.0}", null, false), falseVal);
            assert false;
        } catch (NotOverridenOperationException ex) {
        }
    }

    @Test
    public void testBag() throws Exception {
        OclValue<?> inputVal = TesterTool.evaluate("Bag{1, 2.0}", null, false);

        try {
            executeTest(inputVal, invalid, invalid);
            assert false;
        } catch (NotOverridenOperationException ex) {
        }
        try {
            executeTest(inputVal, nullVal, trueVal);
            assert false;
        } catch (NotOverridenOperationException ex) {
        }
        executeTest(inputVal, TesterTool.evaluate("Set{1, 2.0}", null, false), trueVal);
        executeTest(inputVal, TesterTool.evaluate("OrderedSet{1, 2.0}", null, false), trueVal);
        try {
            executeTest(inputVal, TesterTool.evaluate("Bag{1, 2.0}", null, false), falseVal);
            assert false;
        } catch (NotOverridenOperationException ex) {
        }
        executeTest(inputVal, TesterTool.evaluate("Sequence{1, 2.0}", null, false), trueVal);
    }
}
