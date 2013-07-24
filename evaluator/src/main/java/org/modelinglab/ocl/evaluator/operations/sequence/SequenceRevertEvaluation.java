/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import com.google.common.collect.Lists;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.Reverse;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceRevertEvaluation extends OperationEvaluator {
    
    private SequenceRevertEvaluation() {
    }
    
    public static SequenceRevertEvaluation getInstance() {
        return SequenceRevertEvaluationHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Reverse.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return new SequenceValue<>(Lists.reverse(val.getValue()), val.getType().getElementType(), true);
    }
    
    private static class SequenceRevertEvaluationHolder {

        private static final SequenceRevertEvaluation INSTANCE = new SequenceRevertEvaluation();
    }
}
