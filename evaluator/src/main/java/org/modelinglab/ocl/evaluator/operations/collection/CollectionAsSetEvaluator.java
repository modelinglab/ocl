/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.AsSet;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionAsSetEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionAsSetEvaluator() {
    }
    
    public static CollectionAsSetEvaluator getInstance() {
        return CollectionAsSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsSet.createTemplateOperation();
    }
    
    private static class CollectionAsSetEvaluatorHolder {

        private static final CollectionAsSetEvaluator INSTANCE = new CollectionAsSetEvaluator();
    }
}
