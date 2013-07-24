/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.Prepend;
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
        throw new UnsupportedOperationException(arg.exp.getReferredOperation() + " is not supported yet");
    }

    @Override
    public boolean isImplemented() {
        //TODO: implement me!
        return false;
    }
    
    private static class OrderedSetPrependEvaluatorHolder {

        private static final OrderedSetPrependEvaluator INSTANCE = new OrderedSetPrependEvaluator();
    }
}
