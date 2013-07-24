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
public class CollectionExistsEvaluatorTest extends AbstractTest {
    
    public CollectionExistsEvaluatorTest() {
    }

    @Test
    public void testSet() throws Exception {
        assertEquals("Set{}->exists(i | i.oclIsUndefined())", "false");
        assertEquals("Set{1,2,3}->exists(i | i > 3)", "false");
        assertEquals("Set{1,2,3,4}->exists(i | i > 3)", "true");
        assertEquals("Set{1,2,3,invalid}->exists(i | i > 3)", "invalid");
        assertEquals("Set{1,2,3,invalid, 4}->exists(i | i > 3)", "invalid");
        assertEquals("Set{1,2,3,4,invalid}->exists(i | i > 3)", "true");
    }
    
    @Test
    public void testOrderedSet() throws Exception {
        assertEquals("OrderedSet{}->exists(i | i.oclIsUndefined())", "false");
        assertEquals("OrderedSet{1,2,3}->exists(i | i > 3)", "false");
        assertEquals("OrderedSet{1,2,3,4}->exists(i | i > 3)", "true");
        assertEquals("OrderedSet{1,2,3,invalid}->exists(i | i > 3)", "invalid");
        assertEquals("OrderedSet{1,2,3,invalid, 4}->exists(i | i > 3)", "invalid");
        assertEquals("OrderedSet{1,2,3,4,invalid}->exists(i | i > 3)", "true");
    }
    
    @Test
    public void testBag() throws Exception {
        assertEquals("Bag{}->exists(i | i.oclIsUndefined())", "false");
        assertEquals("Bag{1,2,3}->exists(i | i > 3)", "false");
        assertEquals("Bag{1,2,3,4}->exists(i | i > 3)", "true");
        assertEquals("Bag{1,2,3,invalid}->exists(i | i > 3)", "invalid");
        assertEquals("Bag{1,2,3,invalid, 4}->exists(i | i > 3)", "invalid");
        assertEquals("Bag{1,2,3,4,invalid}->exists(i | i > 3)", "true");
    }
    
    @Test
    public void testSequence() throws Exception {
        assertEquals("Sequence{}->exists(i | i.oclIsUndefined())", "false");
        assertEquals("Sequence{1,2,3}->exists(i | i > 3)", "false");
        assertEquals("Sequence{1,2,3,4}->exists(i | i > 3)", "true");
        assertEquals("Sequence{1,2,3,invalid}->exists(i | i > 3)", "invalid");
        assertEquals("Sequence{1,2,3,invalid, 4}->exists(i | i > 3)", "invalid");
        assertEquals("Sequence{1,2,3,4,invalid}->exists(i | i > 3)", "true");
    }
}
