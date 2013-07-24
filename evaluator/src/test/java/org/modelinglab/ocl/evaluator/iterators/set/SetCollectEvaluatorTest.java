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
public class SetCollectEvaluatorTest extends AbstractTest {
    
    public SetCollectEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Set{}             ->collect(a | a.oclIsUndefined())", "Bag{false}->excluding(false)");
        assertEquals("Set{1,2,3}        ->collect(i | i > 3)", "Bag{false, false, false}");
        assertEquals("Set{4}            ->collect(i | i > 3)", "Bag{true}");
        assertEquals("Set{1,2,3,4}      ->collect(i | i > 3)", "Bag{false, false, false, true}");
        assertEquals("Set{1,2,3,4}      ->collect(i | Set{i})", "Bag{1, 2, 3, 4}");
        assertEquals("Set{1,2,3,invalid}->collect(i | i > 3)", "invalid");
        assertEquals("Set{invalid, 4}   ->collect(i | i > 3)", "invalid");
        assertEquals("Set{4,invalid}    ->collect(i | i > 3)", "invalid");
    }
}
