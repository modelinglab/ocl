/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.orderedSet;

import org.junit.Test;
import org.modelinglab.ocl.evaluator.AbstractTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetCollectNestedEvaluatorTest extends AbstractTest {
    
    public OrderedSetCollectNestedEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("OrderedSet{}             ->collectNested(a | a.oclIsUndefined())", "Sequence{false}->excluding(false)");
        assertEquals("OrderedSet{1,2,3}        ->collectNested(i | i > 3)", "Sequence{false, false, false}");
        assertEquals("OrderedSet{4}            ->collectNested(i | i > 3)", "Sequence{true}");
        assertEquals("OrderedSet{1,2,3,4}      ->collectNested(i | i > 3)", "Sequence{false, false, false, true}");
        assertEquals("OrderedSet{1,2,3,4}      ->collectNested(i | Set{i})", "Sequence{Set{1}, Set{2}, Set{3}, Set{4}}");
        assertEquals("OrderedSet{1,2,3,invalid}->collectNested(i | i > 3)", "invalid");
        assertEquals("OrderedSet{invalid, 4}   ->collectNested(i | i > 3)", "invalid");
        assertEquals("OrderedSet{4,invalid}    ->collectNested(i | i > 3)", "invalid");
    }
}
