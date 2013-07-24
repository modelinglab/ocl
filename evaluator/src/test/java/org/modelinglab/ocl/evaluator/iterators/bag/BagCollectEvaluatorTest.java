/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.bag;

import org.junit.Test;
import org.modelinglab.ocl.evaluator.AbstractTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagCollectEvaluatorTest extends AbstractTest {
    
    public BagCollectEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Bag{}             ->collect(a | a.oclIsUndefined())", "Bag{false}->excluding(false)");
        assertEquals("Bag{1,2,3}        ->collect(i | i > 3)", "Bag{false, false, false}");
        assertEquals("Bag{4}            ->collect(i | i > 3)", "Bag{true}");
        assertEquals("Bag{1,2,3,4}      ->collect(i | i > 3)", "Bag{false, false, false, true}");
        assertEquals("Bag{1,2,3,4}      ->collect(i | Set{i})", "Bag{1, 2, 3, 4}");
        assertEquals("Bag{1,2,3,invalid}->collect(i | i > 3)", "invalid");
        assertEquals("Bag{invalid, 4}   ->collect(i | i > 3)", "invalid");
        assertEquals("Bag{4,invalid}    ->collect(i | i > 3)", "invalid");
    }
}
