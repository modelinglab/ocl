/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.Min;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionMinEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionMinEvaluator() {
    }
    
    public static CollectionMinEvaluator getInstance() {
        return CollectionMinEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Min.createTemplateOperation();
    }

    @Override
    public boolean isImplemented() {
        return false;
    }
    
    private static class CollectionMinEvaluatorHolder {

        private static final CollectionMinEvaluator INSTANCE = new CollectionMinEvaluator();
    }
}
