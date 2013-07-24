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
public class SequenceSelectEvaluatorTest extends AbstractTest {
    
    public SequenceSelectEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Sequence{}             ->select(a | a.oclIsUndefined())", "Sequence{}");
        assertEquals("Sequence{1,2,3}        ->select(i | i > 3)", "Sequence{1}->excluding(1)");
        assertEquals("Sequence{4}            ->select(i | i > 3)", "Sequence{4}");
        assertEquals("Sequence{1,2,3,4}      ->select(i | i > 3)", "Sequence{4}");
        assertEquals("Sequence{1,2,3,invalid}->select(i | i > 3)", "invalid");
        assertEquals("Sequence{invalid, 4}   ->select(i | i > 3)", "invalid");
        assertEquals("Sequence{4,invalid}    ->select(i | i > 3)", "invalid");
    }
}
