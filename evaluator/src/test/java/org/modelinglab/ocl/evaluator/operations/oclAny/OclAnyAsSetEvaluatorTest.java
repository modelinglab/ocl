/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import java.util.LinkedList;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.standard.operations.oclAny.AsSet;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.AbstractUnaryOperationTest;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OclAnyAsSetEvaluatorTest extends AbstractUnaryOperationTest {
    
    public OclAnyAsSetEvaluatorTest() {
        super(AsSet.createTemplateOperation());
    }

    @Test
    public void test() throws Exception {
        executeTest(invalid, invalid);
        executeTest(nullVal, new SetValue<>(new LinkedList<OclValue<?>>(), AnyType.getInstance()));
        
        OclValue<?> val = new IntegerValue(23l);
        executeTest(val, createSet(val));
        
    }
    
    private <E extends OclValue<?>> SetValue<E> createSet(E value) {
        LinkedList<E> list = new LinkedList<>();
        list.add(value);
        return new SetValue<>(list, value.getType());
    }
}
