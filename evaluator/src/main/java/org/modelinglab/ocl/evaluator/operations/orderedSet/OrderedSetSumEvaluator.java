/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.Sum;
import org.modelinglab.ocl.evaluator.operations.collection.CollectionOperationAbstractEvaluator;

/**
 *
 */
public class OrderedSetSumEvaluator extends CollectionOperationAbstractEvaluator {
    
    private OrderedSetSumEvaluator() {
    }
    
    public static OrderedSetSumEvaluator getInstance() {
        return OrderedSetSumEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Sum.createTemplateOperation();
    }

    @Override
    public boolean isImplemented() {
        return false;
    }
    
    private static class OrderedSetSumEvaluatorHolder {

        private static final OrderedSetSumEvaluator INSTANCE = new OrderedSetSumEvaluator();
    }
}