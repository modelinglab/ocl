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
public class TupleAttributeCallExpEvalTest extends AbstractTest {
    
    public TupleAttributeCallExpEvalTest() {
    }

    @Test
    public void test() throws Exception {
        assertEquals("Tuple{a : Integer = 3, b : Real = 2}.a", "3");
        assertEquals("Tuple{a : Integer = 3, b : Real = 2}.b", "2");
        assertEquals("Set{Tuple{a : Integer = 3, b : Real = 2}}.a", "Bag{3}");
    }
}
