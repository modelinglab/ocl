/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.AsOrderedSet;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetAsOrderedSetEvaluator extends OperationEvaluator {
    
    private OrderedSetAsOrderedSetEvaluator() {
    }
    
    public static OrderedSetAsOrderedSetEvaluator getInstance() {
        return OrderedSetAsOrderedSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsOrderedSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return val;
    }
    
    private static class OrderedSetAsOrderedSetEvaluatorHolder {

        private static final OrderedSetAsOrderedSetEvaluator INSTANCE = new OrderedSetAsOrderedSetEvaluator();
    }
}
