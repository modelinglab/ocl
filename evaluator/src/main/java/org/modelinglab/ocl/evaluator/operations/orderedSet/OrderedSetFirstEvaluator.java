/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.First;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.ListFirst;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetFirstEvaluator extends OperationEvaluator {
    
    private OrderedSetFirstEvaluator() {
    }
    
    public static OrderedSetFirstEvaluator getInstance() {
        return OrderedSetFirstEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return First.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return ListFirst.visit(val.getValue(), arg);
    }
    
    private static class OrderedSetFirstEvaluatorHolder {

        private static final OrderedSetFirstEvaluator INSTANCE = new OrderedSetFirstEvaluator();
    }
}
