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
public class OrderedSetCollectEvaluatorTest extends AbstractTest {
    
    public OrderedSetCollectEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("OrderedSet{}             ->collect(a | a.oclIsUndefined())", "Sequence{false}->excluding(false)");
        assertEquals("OrderedSet{1,2,3}        ->collect(i | i > 3)", "Sequence{false, false, false}");
        assertEquals("OrderedSet{4}            ->collect(i | i > 3)", "Sequence{true}");
        assertEquals("OrderedSet{1,2,3,4}      ->collect(i | i > 3)", "Sequence{false, false, false, true}");
        assertEquals("OrderedSet{1,2,3,4}      ->collect(i | Set{i})", "Sequence{1, 2, 3, 4}");
        assertEquals("OrderedSet{1,2,3,invalid}->collect(i | i > 3)", "invalid");
        assertEquals("OrderedSet{invalid, 4}   ->collect(i | i > 3)", "invalid");
        assertEquals("OrderedSet{4,invalid}    ->collect(i | i > 3)", "invalid");
    }
}
