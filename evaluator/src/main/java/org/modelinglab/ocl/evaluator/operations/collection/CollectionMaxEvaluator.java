/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.Max;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionMaxEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionMaxEvaluator() {
    }
    
    public static CollectionMaxEvaluator getInstance() {
        return CollectionMaxEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Max.createTemplateOperation();
    }

    @Override
    public boolean isImplemented() {
        return false;
    }
    
    private static class CollectionMaxEvaluatorHolder {

        private static final CollectionMaxEvaluator INSTANCE = new CollectionMaxEvaluator();
    }
}
