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
public class SetSelectEvaluatorTest extends AbstractTest {
    
    public SetSelectEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Set{}             ->select(a | a.oclIsUndefined())", "Set{}");
        assertEquals("Set{1,2,3}        ->select(i | i > 3)", "Set{1}->excluding(1)");
        assertEquals("Set{4}            ->select(i | i > 3)", "Set{4}");
        assertEquals("Set{1,2,3,4}      ->select(i | i > 3)", "Set{4}");
        assertEquals("Set{1,2,3,invalid}->select(i | i > 3)", "invalid");
        assertEquals("Set{invalid, 4}   ->select(i | i > 3)", "invalid");
        assertEquals("Set{4,invalid}    ->select(i | i > 3)", "invalid");
    }
}
