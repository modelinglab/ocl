/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.standard.operations.bag.Flatten;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.CollectionFlatten;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagFlattenEvaluator extends OperationEvaluator {
    
    private BagFlattenEvaluator() {
    }
    
    public static BagFlattenEvaluator getInstance() {
        return BagFlattenEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Flatten.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument arg) {
        Classifier elementType = val.getType().getElementType();
        if (!(elementType.isCollection() || elementType instanceof AnyType)) {
            assert CollectionFlatten.isFlatten(val.getValue());
            return val;
        }

        Flatten op = (Flatten) arg.exp.getReferredOperation();
        BagType resultType = (BagType) op.getType();
        
        SequenceValue<? extends OclValue<?>> resultSeq = CollectionFlatten.flatten(val.getValue(), resultType.getElementType());
        
        return new BagValue<>(resultSeq);
    }
    
    private static class BagFlattenEvaluatorHolder {

        private static final BagFlattenEvaluator INSTANCE = new BagFlattenEvaluator();
    }
}
