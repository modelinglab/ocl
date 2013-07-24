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
public class OrderedSetRejectEvaluatorTest extends AbstractTest {
    
    public OrderedSetRejectEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("OrderedSet{}             ->reject(a | a.oclIsUndefined())", "OrderedSet{}");
        assertEquals("OrderedSet{1,2,3}        ->reject(i | i > 3)", "OrderedSet{1,2,3}");
        assertEquals("OrderedSet{4}            ->reject(i | i > 3)", "Bag{4}->excluding(4)->asOrderedSet()");
        assertEquals("OrderedSet{1,2,3,4}      ->reject(i | i > 3)", "OrderedSet{1, 2, 3}");
        assertEquals("OrderedSet{1,2,3,invalid}->reject(i | i > 3)", "invalid");
        assertEquals("OrderedSet{invalid, 4}   ->reject(i | i > 3)", "invalid");
        assertEquals("OrderedSet{4,invalid}    ->reject(i | i > 3)", "invalid");
    }
}
