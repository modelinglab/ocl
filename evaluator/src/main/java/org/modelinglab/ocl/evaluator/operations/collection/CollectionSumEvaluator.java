/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.Sum;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionSumEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionSumEvaluator() {
    }
    
    public static CollectionSumEvaluator getInstance() {
        return CollectionSumEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Sum.createTemplateOperation();
    }

    @Override
    public boolean isImplemented() {
        return false;
    }
    
    private static class CollectionSumEvaluatorHolder {

        private static final CollectionSumEvaluator INSTANCE = new CollectionSumEvaluator();
    }
}
