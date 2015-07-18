/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import com.google.common.collect.Lists;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.Reverse;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetReverseEvaluator extends OperationEvaluator {
    
    private OrderedSetReverseEvaluator() {
    }
    
    public static OrderedSetReverseEvaluator getInstance() {
        return OrderedSetReverseEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Reverse.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return new OrderedSetValue<>(Lists.reverse(val.getValue()), val.getType().getElementType(), true);
    }
    
    private static class OrderedSetReverseEvaluatorHolder {

        private static final OrderedSetReverseEvaluator INSTANCE = new OrderedSetReverseEvaluator();
    }
}
