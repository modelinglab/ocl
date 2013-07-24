/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.standard.operations.set.Flatten;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.sequence.SequenceAsSetEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.CollectionFlatten;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetFlattenEvaluator extends OperationEvaluator {
    
    private SetFlattenEvaluator() {
    }
    
    public static SetFlattenEvaluator getInstance() {
        return SetFlattenEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Flatten.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        
        Classifier elementType = val.getType().getElementType();
        if (!(elementType.isCollection() || elementType instanceof AnyType)) {
            assert CollectionFlatten.isFlatten(val.getValue());
            return val;
        }

        Flatten op = (Flatten) arg.exp.getReferredOperation();
        SetType resultType = (SetType) op.getType();
        
        SequenceValue<? extends OclValue<?>> resultSeq = CollectionFlatten.flatten(val.getValue(), resultType.getElementType());
        
        return SequenceAsSetEvaluator.getInstance().visit(resultSeq, arg);
    }
    
    private static class SetFlattenEvaluatorHolder {

        private static final SetFlattenEvaluator INSTANCE = new SetFlattenEvaluator();
    }
}
