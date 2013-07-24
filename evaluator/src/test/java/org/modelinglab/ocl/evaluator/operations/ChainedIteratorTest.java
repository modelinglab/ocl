/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations;

import org.junit.Test;
import org.modelinglab.ocl.evaluator.AbstractTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class ChainedIteratorTest extends AbstractTest {
    
    @Test
    public void test() throws Exception {
        String exp = "let set : Set(Integer) = Set{1,2,3,4,5} in "
                + "set->collect(i | set->select(j | j < i)->size())";
        assertEquals(exp, "Bag{0,1,2,3,4}");
    }
    
}
