/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import java.util.ArrayList;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.Append;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetAppendEvaluator extends OperationEvaluator {
    
    private OrderedSetAppendEvaluator() {
    }
    
    public static OrderedSetAppendEvaluator getInstance() {
        return OrderedSetAppendEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Append.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> element = arg.arguments.get(0);
        if (element instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }
        //TODO: gortiz: If we play with very big collections, a view could dramatically improve our performance.
        ArrayList<OclValue<?>> newResult = new ArrayList<>(val.getValue().size());
        newResult.addAll(val.getValue());
        // as ordered set, if the element already exists it must be removed first, before adding it at the end
        newResult.remove(element);
        newResult.add(element);
        
        return new OrderedSetValue<>(newResult, val.getType().getElementType(), true);
    }
    
    private static class OrderedSetAppendEvaluatorHolder {

        private static final OrderedSetAppendEvaluator INSTANCE = new OrderedSetAppendEvaluator();
    }
}
