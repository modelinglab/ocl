/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public abstract class AbstractDateEvaluator extends OperationEvaluator {
    
    public abstract OclValue<?> visitDate(AGDate.AGDateObject val, SwitchArgument arg);
    
    @Override
    public OclValue<?> visit(ObjectValue<?> val, SwitchArgument arg) {
        if (!(val.getValue() instanceof AGDate.AGDateObject)) {
            throw new AssertionError("This OperationEvaluator expects AGDateObjects as source value");
        }
        AGDate.AGDateObject sourceValue = (AGDate.AGDateObject) val.getValue();
        return visitDate(sourceValue, arg);
    }
}
