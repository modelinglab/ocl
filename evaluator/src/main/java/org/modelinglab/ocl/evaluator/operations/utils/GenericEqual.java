/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.utils;

import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class GenericEqual {

    private GenericEqual() {
    }
    
    public static OclValue<?> equal(OclValue<?> val, OperationEvaluator.SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        OclValue<?> argument = arg.arguments.get(0);
        if (argument instanceof VoidValue && !(val instanceof VoidValue)) {
            return BooleanValue.FALSE;
        }
        if (argument instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }
        
        if (val.equals(argument)) {
            return BooleanValue.TRUE;
        }
        return BooleanValue.FALSE;
    }
}
