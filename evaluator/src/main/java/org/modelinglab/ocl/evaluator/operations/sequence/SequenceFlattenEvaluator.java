/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.standard.operations.sequence.Flatten;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.CollectionFlatten;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceFlattenEvaluator extends OperationEvaluator {
    
    private SequenceFlattenEvaluator() {
    }
    
    public static SequenceFlattenEvaluator getInstance() {
        return SequenceFlattenEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Flatten.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        Classifier elementType = val.getType().getElementType();
        if (!(elementType.isCollection() || elementType instanceof AnyType)) {
            assert CollectionFlatten.isFlatten(val.getValue());
            return val;
        }

        Flatten op = (Flatten) arg.exp.getReferredOperation();
        SequenceType resultType = (SequenceType) op.getType();
        
        SequenceValue<? extends OclValue<?>> result = CollectionFlatten.flatten(val.getValue(), resultType.getElementType());
        
        return result;
    }
    
    private static class SequenceFlattenEvaluatorHolder {

        private static final SequenceFlattenEvaluator INSTANCE = new SequenceFlattenEvaluator();
    }
}
