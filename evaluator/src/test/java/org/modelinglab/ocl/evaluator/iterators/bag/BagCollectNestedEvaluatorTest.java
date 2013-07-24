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
public class BagCollectNestedEvaluatorTest extends AbstractTest {
    
    public BagCollectNestedEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Bag{}             ->collectNested(a | a.oclIsUndefined())", "Bag{false}->excluding(false)");
        assertEquals("Bag{1,2,3}        ->collectNested(i | i > 3)", "Bag{false, false, false}");
        assertEquals("Bag{4}            ->collectNested(i | i > 3)", "Bag{true}");
        assertEquals("Bag{1,2,3,4}      ->collectNested(i | i > 3)", "Bag{false, false, false, true}");
        assertEquals("Bag{1,2,3,4}      ->collectNested(i | Set{i})", "Bag{Set{1}, Set{2}, Set{3}, Set{4}}");
        assertEquals("Bag{1,2,3,invalid}->collectNested(i | i > 3)", "invalid");
        assertEquals("Bag{invalid, 4}   ->collectNested(i | i > 3)", "invalid");
        assertEquals("Bag{4,invalid}    ->collectNested(i | i > 3)", "invalid");
    }
}
