/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.set;

import org.junit.Test;
import org.modelinglab.ocl.evaluator.AbstractTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetRejectEvaluatorTest extends AbstractTest {
    
    public SetRejectEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Set{}             ->reject(a | a.oclIsUndefined())", "Set{}");
        assertEquals("Set{1,2,3}        ->reject(i | i > 3)", "Set{1,2,3}");
        assertEquals("Set{4}            ->reject(i | i > 3)", "Set{4}->excluding(4)");
        assertEquals("Set{1,2,3,4}      ->reject(i | i > 3)", "Set{1, 2, 3}");
        assertEquals("Set{1,2,3,invalid}->reject(i | i > 3)", "invalid");
        assertEquals("Set{invalid, 4}   ->reject(i | i > 3)", "invalid");
        assertEquals("Set{4,invalid}    ->reject(i | i > 3)", "invalid");
        
    }
}
