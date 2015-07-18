/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import java.util.ArrayList;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.Prepend;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetPrependEvaluator extends OperationEvaluator {
    
    private OrderedSetPrependEvaluator() {
    }
    
    public static OrderedSetPrependEvaluator getInstance() {
        return OrderedSetPrependEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Prepend.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> element = arg.arguments.get(0);
        if (element instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }
        //TODO: gortiz: If we play with very big collections, a view could dramatically improve our performance.
        ArrayList<OclValue<?>> tail = new ArrayList<>(val.getValue().size());
        tail.addAll(val.getValue());
        // as ordered set, if the element already exists it must be removed first, before adding it at the beginning
        tail.remove(element);
        ArrayList<OclValue<?>> newResult = new ArrayList<>(tail.size() + 1);
        newResult.add(element);
        newResult.addAll(tail);
        
        return new OrderedSetValue<>(newResult, val.getType().getElementType(), true);
    }
    
    private static class OrderedSetPrependEvaluatorHolder {

        private static final OrderedSetPrependEvaluator INSTANCE = new OrderedSetPrependEvaluator();
    }
}
