/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.Append;
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
        throw new UnsupportedOperationException(arg.exp.getReferredOperation() + " is not supported yet");
    }

    @Override
    public boolean isImplemented() {
        //TODO: implement me!
        return false;
    }
    
    private static class OrderedSetAppendEvaluatorHolder {

        private static final OrderedSetAppendEvaluator INSTANCE = new OrderedSetAppendEvaluator();
    }
}
