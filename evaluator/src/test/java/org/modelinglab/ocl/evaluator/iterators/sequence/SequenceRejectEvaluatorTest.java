/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.sequence;

import org.junit.Test;
import org.modelinglab.ocl.evaluator.AbstractTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceRejectEvaluatorTest extends AbstractTest {
    
    public SequenceRejectEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Sequence{}             ->collect(a | a.oclIsUndefined())", "Sequence{false}->excluding(false)");
        assertEquals("Sequence{1,2,3}        ->collect(i | i > 3)", "Sequence{false, false, false}");
        assertEquals("Sequence{4}            ->collect(i | i > 3)", "Sequence{true}");
        assertEquals("Sequence{1,2,3,4}      ->collect(i | i > 3)", "Sequence{false, false, false, true}");
        assertEquals("Sequence{1,2,3,4}      ->collect(i | Set{i})", "Sequence{1, 2, 3, 4}");
        assertEquals("Sequence{1,2,3,invalid}->collect(i | i > 3)", "invalid");
        assertEquals("Sequence{invalid, 4}   ->collect(i | i > 3)", "invalid");
        assertEquals("Sequence{4,invalid}    ->collect(i | i > 3)", "invalid");
    }
}
