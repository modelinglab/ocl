/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.collection;

import org.junit.Test;
import org.modelinglab.ocl.evaluator.AbstractTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionForAllEvaluatorTest extends AbstractTest {
    
    public CollectionForAllEvaluatorTest() {
    }

    @Test
    public void testSet() throws Exception {
        assertEquals("Set{}->forAll(a | a.oclIsUndefined())", "true");
        assertEquals("Set{1,2,3}->forAll(i | i > 3)", "false");
        assertEquals("Set{4}->forAll(i | i > 3)", "true");
        assertEquals("Set{1,2,3,invalid}->forAll(i | i > 3)", "false");
        assertEquals("Set{invalid, 4}->forAll(i | i > 3)", "invalid");
        assertEquals("Set{4,invalid}->forAll(i | i > 3)", "invalid");
    }
    
    @Test
    public void testOrderedSet() throws Exception {
        assertEquals("OrderedSet{}->forAll(a | a.oclIsUndefined())", "true");
        assertEquals("OrderedSet{1,2,3}->forAll(i | i > 3)", "false");
        assertEquals("OrderedSet{4}->forAll(i | i > 3)", "true");
        assertEquals("OrderedSet{1,2,3,invalid}->forAll(i | i > 3)", "false");
        assertEquals("OrderedSet{invalid, 4}->forAll(i | i > 3)", "invalid");
        assertEquals("OrderedSet{4,invalid}->forAll(i | i > 3)", "invalid");
    }
    
    @Test
    public void testBag() throws Exception {
        assertEquals("Bag{}->forAll(a | a.oclIsUndefined())", "true");
        assertEquals("Bag{1,2,3}->forAll(i | i > 3)", "false");
        assertEquals("Bag{4}->forAll(i | i > 3)", "true");
        assertEquals("Bag{1,2,3,invalid}->forAll(i | i > 3)", "false");
        assertEquals("Bag{invalid, 4}->forAll(i | i > 3)", "invalid");
        assertEquals("Bag{4,invalid}->forAll(i | i > 3)", "invalid");
    }
    
    @Test
    public void testSequence() throws Exception {
        assertEquals("Sequence{}->forAll(a | a.oclIsUndefined())", "true");
        assertEquals("Sequence{1,2,3}->forAll(i | i > 3)", "false");
        assertEquals("Sequence{4}->forAll(i | i > 3)", "true");
        assertEquals("Sequence{1,2,3,invalid}->forAll(i | i > 3)", "false");
        assertEquals("Sequence{invalid, 4}->forAll(i | i > 3)", "invalid");
        assertEquals("Sequence{4,invalid}->forAll(i | i > 3)", "invalid");
    }
}
