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
public class IfExpEvalTest extends AbstractTest {
    
    public IfExpEvalTest() {
    }

    @Test
    public void basicTest() throws Exception {
        assertEquals("if (true) then 1 else 2 endif", "1");
        assertEquals("if (false) then 1 else 2 endif", "2");
    }
    
    @Test
    public void invalidTest() throws Exception {
        assertEquals("if (invalid) then 1 else 2 endif", "invalid");
    }
    
    @Test
    public void nullTest() throws Exception {
        assertEquals("if (null) then 1 else 2 endif", "invalid");
    }

    @Test
    public void invalidInOtherBranchTest() throws Exception {        
        assertEquals("if (invalid.oclIsInvalid()) then 1 else invalid endif", "1");
        assertEquals("if (false) then invalid else 2 endif", "2");
    }
}
