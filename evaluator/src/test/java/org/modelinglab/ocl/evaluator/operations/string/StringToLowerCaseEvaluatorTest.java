/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.junit.Test;
import org.modelinglab.ocl.core.standard.operations.string.ToLowerCase;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringToLowerCaseEvaluatorTest extends AbstractUnaryOperationTest {
    
    public StringToLowerCaseEvaluatorTest() {
        super(ToLowerCase.getInstance());
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(new StringValue(""), new StringValue(""));
        executeTest(new StringValue("aSDf"), new StringValue("asdf"));
        executeTest(new StringValue("fdsa"), new StringValue("fdsa"));
    }
}
