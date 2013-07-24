/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.collection;

import org.junit.Test;
import org.modelinglab.ocl.evaluator.AbstractTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionIsUniqueEvaluatorTest extends AbstractTest {
    
    public CollectionIsUniqueEvaluatorTest() {
    }

    @Test
    public void testSet() throws Exception {
        assertEquals("Set{}->isUnique(a | a)", "true");
        assertEquals("Set{1,2,3}->isUnique(i | i)", "true");
        assertEquals("Set{1,2,3}->isUnique(i | i < 2)", "false");
        assertEquals("Set{1,2,3,invalid}->isUnique(i | i > 3)", "false");
        assertEquals("Set{invalid, 4}->isUnique(i | i > 3)", "invalid");
        assertEquals("Set{4,invalid}->isUnique(i | i > 3)", "invalid");
    }
    
    @Test
    public void testOrderedSet() throws Exception {
        assertEquals("OrderedSet{}->isUnique(a | a)", "true");
        assertEquals("OrderedSet{1,2,3}->isUnique(i | i)", "true");
        assertEquals("OrderedSet{1,2,3}->isUnique(i | i < 2)", "false");
        assertEquals("OrderedSet{1,2,3,invalid}->isUnique(i | i > 3)", "false");
        assertEquals("OrderedSet{invalid, 4}->isUnique(i | i > 3)", "invalid");
        assertEquals("OrderedSet{4,invalid}->isUnique(i | i > 3)", "invalid");
    }
    
    @Test
    public void testBag() throws Exception {
        assertEquals("Bag{}->isUnique(a | a)", "true");
        assertEquals("Bag{1,2,3}->isUnique(i | i)", "true");
        assertEquals("Bag{1,2,3}->isUnique(i | i < 2)", "false");
        assertEquals("Bag{1,2,3,invalid}->isUnique(i | i > 3)", "false");
        assertEquals("Bag{invalid, 4}->isUnique(i | i > 3)", "invalid");
        assertEquals("Bag{4,invalid}->isUnique(i | i > 3)", "invalid");
    }
    
    @Test
    public void testSequence() throws Exception {
        assertEquals("Sequence{}->isUnique(a | a)", "true");
        assertEquals("Sequence{1,2,3}->isUnique(i | i)", "true");
        assertEquals("Sequence{1,2,3}->isUnique(i | i < 2)", "false");
        assertEquals("Sequence{1,2,3,invalid}->isUnique(i | i > 3)", "false");
        assertEquals("Sequence{invalid, 4}->isUnique(i | i > 3)", "invalid");
        assertEquals("Sequence{4,invalid}->isUnique(i | i > 3)", "invalid");
    }
}
