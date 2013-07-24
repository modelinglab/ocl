/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.AsSet;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetAsSetEvaluator extends OperationEvaluator {
    
    private OrderedSetAsSetEvaluator() {
    }
    
    public static OrderedSetAsSetEvaluator getInstance() {
        return OrderedSetAsSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return new SetValue<>(val);
    }
    
    private static class OrderedSetAsSetEvaluatorHolder {

        private static final OrderedSetAsSetEvaluator INSTANCE = new OrderedSetAsSetEvaluator();
    }
}
