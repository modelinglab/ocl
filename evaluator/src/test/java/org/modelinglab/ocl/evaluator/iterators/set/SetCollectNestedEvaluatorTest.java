/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.set;

import org.junit.Test;
import org.modelinglab.ocl.evaluator.AbstractTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetCollectNestedEvaluatorTest extends AbstractTest {
    
    public SetCollectNestedEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Set{}             ->collectNested(a | a.oclIsUndefined())", "Bag{false}->excluding(false)");
        assertEquals("Set{1,2,3}        ->collectNested(i | i > 3)", "Bag{false, false, false}");
        assertEquals("Set{4}            ->collectNested(i | i > 3)", "Bag{true}");
        assertEquals("Set{1,2,3,4}      ->collectNested(i | i > 3)", "Bag{false, false, false, true}");
        assertEquals("Set{1,2,3,4}      ->collectNested(i | Set{i})", "Bag{Set{1}, Set{2}, Set{3}, Set{4}}");
        assertEquals("Set{1,2,3,invalid}->collectNested(i | i > 3)", "invalid");
        assertEquals("Set{invalid, 4}   ->collectNested(i | i > 3)", "invalid");
        assertEquals("Set{4,invalid}    ->collectNested(i | i > 3)", "invalid");
    }
}
