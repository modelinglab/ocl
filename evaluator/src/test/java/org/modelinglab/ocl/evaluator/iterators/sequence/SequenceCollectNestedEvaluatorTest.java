/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.sequence;

import org.junit.Test;
import org.modelinglab.ocl.evaluator.AbstractTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceCollectNestedEvaluatorTest extends AbstractTest {
    
    public SequenceCollectNestedEvaluatorTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Sequence{}             ->collectNested(a | a.oclIsUndefined())", "Sequence{false}->excluding(false)");
        assertEquals("Sequence{1,2,3}        ->collectNested(i | i > 3)", "Sequence{false, false, false}");
        assertEquals("Sequence{4}            ->collectNested(i | i > 3)", "Sequence{true}");
        assertEquals("Sequence{1,2,3,4}      ->collectNested(i | i > 3)", "Sequence{false, false, false, true}");
        assertEquals("Sequence{1,2,3,4}      ->collectNested(i | Set{i})", "Sequence{Set{1}, Set{2}, Set{3}, Set{4}}");
        assertEquals("Sequence{1,2,3,invalid}->collectNested(i | i > 3)", "invalid");
        assertEquals("Sequence{invalid, 4}   ->collectNested(i | i > 3)", "invalid");
        assertEquals("Sequence{4,invalid}    ->collectNested(i | i > 3)", "invalid");
    }
}
