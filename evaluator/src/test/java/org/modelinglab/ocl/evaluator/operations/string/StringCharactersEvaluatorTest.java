/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.standard.operations.string.Characters;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringCharactersEvaluatorTest extends AbstractUnaryOperationTest {
    
    public StringCharactersEvaluatorTest() {
        super(Characters.getInstance());
    }

    @Test
    public void test() throws Exception {
        PrimitiveType stringType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING);
        
        executeTest(invalid, invalid);
        executeTest(nullVal, invalid);
        executeTest(new StringValue(""), new SequenceValue<>(Collections.<StringValue>emptySet(), stringType));
        
        
        ArrayList<StringValue> expectedList = new ArrayList<>(3);
        expectedList.add(new StringValue("a"));
        expectedList.add(new StringValue("s"));
        expectedList.add(new StringValue("d"));
        
        executeTest(new StringValue("asd"), new SequenceValue<>(expectedList, stringType, true));
    }
}
