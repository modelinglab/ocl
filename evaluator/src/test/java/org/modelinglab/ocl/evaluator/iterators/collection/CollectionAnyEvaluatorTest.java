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
public class CollectionAnyEvaluatorTest extends AbstractTest {
    
    public CollectionAnyEvaluatorTest() {
    }

    @Test
    public void testSet() throws Exception {
        assertEquals("Set{}->any(a| a.oclIsUndefined())", "null");
        assertEquals("Set{1,2,3}->any(i | i > 3)", "null");
        assertEquals("Set{1,2,3,4}->any(i | i > 3)", "4");
        assertEquals("Set{1,2,3,invalid}->any(i | i > 3)", "invalid");
        assertEquals("Set{1,2,3,invalid, 4}->any(i | i > 3)", "invalid");
        assertEquals("Set{1,2,3,4,invalid}->any(i | i > 3)", "4");
    }
    
    @Test
    public void testOrderedSet() throws Exception {
        assertEquals("OrderedSet{}->any(a| a.oclIsUndefined())", "null");
        assertEquals("OrderedSet{1,2,3}->any(i | i > 3)", "null");
        assertEquals("OrderedSet{1,2,3,4}->any(i | i > 3)", "4");
        assertEquals("OrderedSet{1,2,3,invalid}->any(i | i > 3)", "invalid");
        assertEquals("OrderedSet{1,2,3,invalid, 4}->any(i | i > 3)", "invalid");
        assertEquals("OrderedSet{1,2,3,4,invalid}->any(i | i > 3)", "4");
    }
    
    @Test
    public void testBag() throws Exception {
        assertEquals("Bag{}->any(a| a.oclIsUndefined())", "null");
        assertEquals("Bag{1,2,3}->any(i | i > 3)", "null");
        assertEquals("Bag{1,2,3,4}->any(i | i > 3)", "4");
        assertEquals("Bag{1,2,3,invalid}->any(i | i > 3)", "invalid");
        assertEquals("Bag{1,2,3,invalid, 4}->any(i | i > 3)", "invalid");
        assertEquals("Bag{1,2,3,4,invalid}->any(i | i > 3)", "4");
    }
    
    @Test
    public void testSequence() throws Exception {
        assertEquals("Sequence{}->any(a| a.oclIsUndefined())", "null");
        assertEquals("Sequence{1,2,3}->any(i | i > 3)", "null");
        assertEquals("Sequence{1,2,3,4}->any(i | i > 3)", "4");
        assertEquals("Sequence{1,2,3,invalid}->any(i | i > 3)", "invalid");
        assertEquals("Sequence{1,2,3,invalid, 4}->any(i | i > 3)", "invalid");
        assertEquals("Sequence{1,2,3,4,invalid}->any(i | i > 3)", "4");
    }
}
