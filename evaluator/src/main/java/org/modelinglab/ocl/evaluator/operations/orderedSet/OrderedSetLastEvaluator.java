/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.Last;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.ListLast;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetLastEvaluator extends OperationEvaluator {
    
    private OrderedSetLastEvaluator() {
    }
    
    public static OrderedSetLastEvaluator getInstance() {
        return OrderedSetLastEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Last.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return ListLast.listLast(val.getValue(), arg);
    }
    
    private static class OrderedSetLastEvaluatorHolder {

        private static final OrderedSetLastEvaluator INSTANCE = new OrderedSetLastEvaluator();
    }
}
