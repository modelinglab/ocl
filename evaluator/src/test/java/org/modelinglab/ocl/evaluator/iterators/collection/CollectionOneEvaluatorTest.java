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
public class CollectionOneEvaluatorTest extends AbstractTest{
    
    public CollectionOneEvaluatorTest() {
    }

    @Test
    public void testSet() throws Exception {
        assertEquals("Set{}             ->one(a | a.oclIsUndefined())", "false");
        assertEquals("Set{1,2,3}        ->one(i | i > 3)", "false");
        assertEquals("Set{4}            ->one(i | i > 3)", "true");
        assertEquals("Set{4,5}          ->one(i | i > 3)", "false");
        assertEquals("Set{1,2,3,invalid}->one(i | i > 3)", "invalid");
        assertEquals("Set{invalid, 4}   ->one(i | i > 3)", "invalid");
        assertEquals("Set{4,invalid}    ->one(i | i > 3)", "invalid");
    }
    
    @Test
    public void testOrderedSet() throws Exception {
        assertEquals("OrderedSet{}             ->one(a | a.oclIsUndefined())", "false");
        assertEquals("OrderedSet{1,2,3}        ->one(i | i > 3)", "false");
        assertEquals("OrderedSet{4}            ->one(i | i > 3)", "true");
        assertEquals("OrderedSet{4,5}          ->one(i | i > 3)", "false");
        assertEquals("OrderedSet{1,2,3,invalid}->one(i | i > 3)", "invalid");
        assertEquals("OrderedSet{invalid, 4}   ->one(i | i > 3)", "invalid");
        assertEquals("OrderedSet{4,invalid}    ->one(i | i > 3)", "invalid");
    }
    
    @Test
    public void testBag() throws Exception {
        assertEquals("Bag{}             ->one(a | a.oclIsUndefined())", "false");
        assertEquals("Bag{1,2,3}        ->one(i | i > 3)", "false");
        assertEquals("Bag{4}            ->one(i | i > 3)", "true");
        assertEquals("Bag{4,5}          ->one(i | i > 3)", "false");
        assertEquals("Bag{1,2,3,invalid}->one(i | i > 3)", "invalid");
        assertEquals("Bag{invalid, 4}   ->one(i | i > 3)", "invalid");
        assertEquals("Bag{4,invalid}    ->one(i | i > 3)", "invalid");
    }
    
    @Test
    public void testSequence() throws Exception {
        assertEquals("Sequence{}             ->one(a | a.oclIsUndefined())", "false");
        assertEquals("Sequence{1,2,3}        ->one(i | i > 3)", "false");
        assertEquals("Sequence{4}            ->one(i | i > 3)", "true");
        assertEquals("Sequence{4,5}          ->one(i | i > 3)", "false");
        assertEquals("Sequence{1,2,3,invalid}->one(i | i > 3)", "invalid");
        assertEquals("Sequence{invalid, 4}   ->one(i | i > 3)", "invalid");
        assertEquals("Sequence{4,invalid}    ->one(i | i > 3)", "invalid");
    }
}
