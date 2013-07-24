/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.AsBag;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionAsBagEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionAsBagEvaluator() {
    }
    
    public static CollectionAsBagEvaluator getInstance() {
        return CollectionAsBagEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsBag.createTemplateOperation();
    }
    
    private static class CollectionAsBagEvaluatorHolder {

        private static final CollectionAsBagEvaluator INSTANCE = new CollectionAsBagEvaluator();
    }
}
