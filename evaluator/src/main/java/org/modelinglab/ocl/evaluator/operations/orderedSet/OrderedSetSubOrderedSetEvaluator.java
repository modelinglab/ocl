/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.SubOrderedSet;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.ListSubList;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetSubOrderedSetEvaluator extends OperationEvaluator {
    
    private OrderedSetSubOrderedSetEvaluator() {
    }
    
    public static OrderedSetSubOrderedSetEvaluator getInstance() {
        return OrderedSetSubOrderedSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return SubOrderedSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return ListSubList.subOrderedSet(val.getValue(), arg, val.getType().getElementType());
    }
    
    private static class OrderedSetSubOrderedSetEvaluatorHolder {

        private static final OrderedSetSubOrderedSetEvaluator INSTANCE = new OrderedSetSubOrderedSetEvaluator();
    }
}
