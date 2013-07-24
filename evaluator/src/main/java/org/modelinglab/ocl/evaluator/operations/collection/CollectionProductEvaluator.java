/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.Product;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionProductEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionProductEvaluator() {
    }
    
    public static CollectionProductEvaluator getInstance() {
        return CollectionProductEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Product.createTemplateOperation();
    }

    @Override
    public boolean isImplemented() {
        return false;
    }
    
    private static class CollectionProductEvaluatorHolder {

        private static final CollectionProductEvaluator INSTANCE = new CollectionProductEvaluator();
    }
}
