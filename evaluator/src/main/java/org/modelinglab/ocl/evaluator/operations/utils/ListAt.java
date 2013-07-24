/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.utils;

import java.util.List;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 * This class contains the semantic of Sequence::at(index: Integer) and OrderedSet::at(index: Integer), which
 * are the same
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class ListAt {

    private ListAt() {
    }

    public static OclValue<?> visit(List<? extends OclValue<?>> list, OperationEvaluator.SwitchArgument arg) {
        OclValue<?> abstractIndexVal = arg.arguments.get(0);
        if (abstractIndexVal instanceof VoidValue || abstractIndexVal instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }
        IntegerValue indexVal = (IntegerValue) abstractIndexVal;
        if (indexVal.getValue() < 1) {
            return InvalidValue.instantiate();
        }
        if (indexVal.getValue() > Integer.MAX_VALUE) {
            throw new OclEvaluationException(
                    arg.exp, 
                    indexVal.getValue() + " is to big to be a a valid argument in "
                    + arg.exp.getReferredOperation() + " operation.");
        }
        int index = indexVal.getValue().intValue();
        if (index > list.size()) {
            return InvalidValue.instantiate();
        }
        return list.get(index - 1); //OCL indexs starts in 1!
    }
    
}
