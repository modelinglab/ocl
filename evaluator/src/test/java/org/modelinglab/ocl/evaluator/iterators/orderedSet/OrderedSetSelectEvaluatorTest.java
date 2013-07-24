/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.orderedSet;

import org.junit.Test;
import org.modelinglab.ocl.evaluator.AbstractTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetSelectEvaluatorTest extends AbstractTest {
    
    public OrderedSetSelectEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("OrderedSet{}             ->select(a | a.oclIsUndefined())", "OrderedSet{}");
        assertEquals("OrderedSet{1,2,3}        ->select(i | i > 3)", "Bag{1}->excluding(1)->asOrderedSet()");
        assertEquals("OrderedSet{4}            ->select(i | i > 3)", "OrderedSet{4}");
        assertEquals("OrderedSet{1,2,3,4}      ->select(i | i > 3)", "OrderedSet{4}");
        assertEquals("OrderedSet{1,2,3,invalid}->select(i | i > 3)", "invalid");
        assertEquals("OrderedSet{invalid, 4}   ->select(i | i > 3)", "invalid");
        assertEquals("OrderedSet{4,invalid}    ->select(i | i > 3)", "invalid");
    }
}
