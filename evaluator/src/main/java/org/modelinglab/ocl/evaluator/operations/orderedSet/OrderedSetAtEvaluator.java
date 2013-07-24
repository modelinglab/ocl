/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.At;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.ListAt;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetAtEvaluator extends OperationEvaluator {

    private OrderedSetAtEvaluator() {
    }

    public static OrderedSetAtEvaluator getInstance() {
        return OrderedSetAtEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return At.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return ListAt.visit(val.getValue(), arg);
    }

    private static class OrderedSetAtEvaluatorHolder {

        private static final OrderedSetAtEvaluator INSTANCE = new OrderedSetAtEvaluator();
    }
}
