/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.Sum;
import org.modelinglab.ocl.evaluator.operations.collection.CollectionOperationAbstractEvaluator;

/**
 *
 */
public class SequenceSumEvaluator extends CollectionOperationAbstractEvaluator {
    
    private SequenceSumEvaluator() {
    }
    
    public static SequenceSumEvaluator getInstance() {
        return SequenceSumEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Sum.createTemplateOperation();
    }

    @Override
    public boolean isImplemented() {
        return false;
    }
    
    private static class SequenceSumEvaluatorHolder {

        private static final SequenceSumEvaluator INSTANCE = new SequenceSumEvaluator();
    }
}