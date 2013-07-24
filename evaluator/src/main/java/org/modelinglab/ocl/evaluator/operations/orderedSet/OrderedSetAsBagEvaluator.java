/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.AsBag;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetAsBagEvaluator extends OperationEvaluator {
    
    private OrderedSetAsBagEvaluator() {
    }
    
    public static OrderedSetAsBagEvaluator getInstance() {
        return OrderedSetAsBagEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsBag.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return new BagValue<>(val);
    }
    
    private static class OrderedSetAsBagEvaluatorHolder {

        private static final OrderedSetAsBagEvaluator INSTANCE = new OrderedSetAsBagEvaluator();
    }
}
