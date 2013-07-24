/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.operations.collection.Flatten;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.utils.CollectionFlatten;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionFlattenEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionFlattenEvaluator() {
    }
    
    public static CollectionFlattenEvaluator getInstance() {
        return CollectionFlattenEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Flatten.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        Classifier elementType = val.getType().getElementType();
        if (!(elementType.isCollection() || elementType instanceof AnyType)) {
            assert CollectionFlatten.isFlatten(val.getValue());
            return val;
        }

        Flatten op = (Flatten) arg.exp.getReferredOperation();
        CollectionType resultType = (CollectionType) op.getType();
        
        SequenceValue<? extends OclValue<?>> resultSeq = CollectionFlatten.flatten(val.getValue(), resultType.getElementType());
        return resultSeq;
    }
    
    private static class CollectionFlattenEvaluatorHolder {

        private static final CollectionFlattenEvaluator INSTANCE = new CollectionFlattenEvaluator();

        private CollectionFlattenEvaluatorHolder() {
        }
    }
}
