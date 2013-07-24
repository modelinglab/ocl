/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.IndexOf;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.ListIndexOf;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetIndexOfEvaluator extends OperationEvaluator {
    
    private OrderedSetIndexOfEvaluator() {
    }
    
    public static OrderedSetIndexOfEvaluator getInstance() {
        return OrderedSetIndexOfEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IndexOf.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return ListIndexOf.visit(val.getValue(), arg);
    }
    
    private static class OrderedSetIndexOfEvaluatorHolder {

        private static final OrderedSetIndexOfEvaluator INSTANCE = new OrderedSetIndexOfEvaluator();
    }
}
