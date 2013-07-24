/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.utils;

import java.util.List;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class ListFirst {
    
    private ListFirst() {}
    
    public static OclValue<?> visit(List<? extends OclValue<?>> val, OperationEvaluator.SwitchArgument arg) {
        if (val.isEmpty()) {
            return InvalidValue.instantiate();
        }
        return val.get(0);
    }
}
