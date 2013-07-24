/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.orderedSet.AsSequence;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetAsSequenceEvaluator extends OperationEvaluator {
    
    private OrderedSetAsSequenceEvaluator() {
    }
    
    public static OrderedSetAsSequenceEvaluator getInstance() {
        return OrderedSetAsSequenceEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsSequence.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return new SequenceValue<>(val);
    }
    
    private static class OrderedSetAsSequenceEvaluatorHolder {

        private static final OrderedSetAsSequenceEvaluator INSTANCE = new OrderedSetAsSequenceEvaluator();
    }
}
