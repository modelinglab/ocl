/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.AsSequence;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionAsSequenceEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionAsSequenceEvaluator() {
    }
    
    public static CollectionAsSequenceEvaluator getInstance() {
        return CollectionAsSequenceEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsSequence.createTemplateOperation();
    }
    
    private static class CollectionAsSequenceEvaluatorHolder {

        private static final CollectionAsSequenceEvaluator INSTANCE = new CollectionAsSequenceEvaluator();
    }
}
