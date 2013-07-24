/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.utils;

import java.util.List;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 * @author Gonzalo Ortiz Jaureguizar
 */
public class ListIndexOf {

    private ListIndexOf() {
    }

    /**
     * OCL 2.3.1 says that is a precondition that source collection must contain the element, so if the
     * element is not contained, we return invalid. This is different in String::indexOf(s:String):Integer,
     * which returns 0 when s is not present in source string!!!
     *
     * @param list
     * @param arg
     *
     * @return the position (1 based) of the element <b>or invalid if the element is not present</b>
     */
    public static OclValue<?> visit(List<? extends OclValue<?>> list, OperationEvaluator.SwitchArgument arg) {
        OclValue<?> element = arg.arguments.get(0);
        if (element instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }

        int index = list.indexOf(element);
        if (index < 0) { //val.getValue().contains(element) == false
            return InvalidValue.instantiate();
        }
        return new IntegerValue((long) index + 1); //OCL indexes starts at 1!
    }
}
