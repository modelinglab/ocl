/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.evaluators;

import org.junit.Test;
import org.modelinglab.ocl.evaluator.AbstractTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class LetExpEvalTest extends AbstractTest {
    
    public LetExpEvalTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("let a : Integer = 3 in a", "3");
    }
}
