/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.utils;

import java.util.List;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class ListLast {

    private ListLast() {
    }
    
    public static OclValue<?> listLast(List<? extends OclValue<?>> list, OperationEvaluator.SwitchArgument arg) {
        if (list.isEmpty()) {
            return InvalidValue.instantiate();
        }
        return list.get(list.size() - 1);
    }
    
}
