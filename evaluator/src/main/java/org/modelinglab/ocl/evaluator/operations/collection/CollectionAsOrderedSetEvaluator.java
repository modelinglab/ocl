/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.AsOrderedSet;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionAsOrderedSetEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionAsOrderedSetEvaluator() {
    }
    
    public static CollectionAsOrderedSetEvaluator getInstance() {
        return CollectionAsOrderedSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsOrderedSet.createTemplateOperation();
    }
    
    private static class CollectionAsOrderedSetEvaluatorHolder {

        private static final CollectionAsOrderedSetEvaluator INSTANCE = new CollectionAsOrderedSetEvaluator();
    }
}
