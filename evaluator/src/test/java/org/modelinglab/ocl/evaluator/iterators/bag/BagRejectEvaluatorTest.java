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
public class BagRejectEvaluatorTest extends AbstractTest {
    
    public BagRejectEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Bag{}             ->reject(a | a.oclIsUndefined())", "Bag{}");
        assertEquals("Bag{1,2,3}        ->reject(i | i > 3)", "Bag{1,2,3}");
        assertEquals("Bag{4}            ->reject(i | i > 3)", "Bag{4}->excluding(4)");
        assertEquals("Bag{1,2,3,4}      ->reject(i | i > 3)", "Bag{1, 2, 3}");
        assertEquals("Bag{1,2,3,invalid}->reject(i | i > 3)", "invalid");
        assertEquals("Bag{invalid, 4}   ->reject(i | i > 3)", "invalid");
        assertEquals("Bag{4,invalid}    ->reject(i | i > 3)", "invalid");
        
    }
}
