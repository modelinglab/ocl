/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.ArrayList;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.Including;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceIncludingEvaluator extends OperationEvaluator {
    
    private SequenceIncludingEvaluator() {
    }
    
    public static SequenceIncludingEvaluator getInstance() {
        return SequenceIncludingEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Including.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> newElement = arg.arguments.get(0);
        if (newElement instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }
        
        List<OclValue<?>> newCol = new ArrayList<>(val.getValue().size() + 1);
        newCol.addAll(val.getValue());
        newCol.add(newElement);
        return new SequenceValue<>(newCol, val.getType().getElementType(), true);
    }
    
    private static class SequenceIncludingEvaluatorHolder {

        private static final SequenceIncludingEvaluator INSTANCE = new SequenceIncludingEvaluator();
    }
}
