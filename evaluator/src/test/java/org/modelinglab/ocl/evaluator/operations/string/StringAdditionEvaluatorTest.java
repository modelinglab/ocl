/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringAdditionEvaluatorTest {
    
    public StringAdditionEvaluatorTest() {
    }

    @Test
    public void test() {
        /*
         * Our parser translate aString + anotherString to aString.concat(anotherString), so we can not test
         * the operation using the parser. We should do it by hand in future.
         */
        //TODO: test String::+(s:String):String operation evaluator
    }
}
