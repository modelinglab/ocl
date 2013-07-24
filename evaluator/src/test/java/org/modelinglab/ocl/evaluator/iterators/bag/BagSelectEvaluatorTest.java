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
public class BagSelectEvaluatorTest extends AbstractTest {
    
    public BagSelectEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Bag{}             ->select(a | a.oclIsUndefined())", "Bag{}");
        assertEquals("Bag{1,2,3}        ->select(i | i > 3)", "Bag{1}->excluding(1)");
        assertEquals("Bag{4}            ->select(i | i > 3)", "Bag{4}");
        assertEquals("Bag{1,2,3,4}      ->select(i | i > 3)", "Bag{4}");
        assertEquals("Bag{1,2,3,invalid}->select(i | i > 3)", "invalid");
        assertEquals("Bag{invalid, 4}   ->select(i | i > 3)", "invalid");
        assertEquals("Bag{4,invalid}    ->select(i | i > 3)", "invalid");
    }
}
