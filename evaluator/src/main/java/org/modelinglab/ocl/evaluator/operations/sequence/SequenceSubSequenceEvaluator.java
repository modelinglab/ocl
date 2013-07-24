/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.SubSequence;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.ListSubList;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceSubSequenceEvaluator extends OperationEvaluator{
    
    private SequenceSubSequenceEvaluator() {
    }
    
    public static SequenceSubSequenceEvaluator getInstance() {
        return SequenceSubSequenceEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return SubSequence.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return ListSubList.subSequence(val.getValue(), arg, val.getType().getElementType());
    }
    
    private static class SequenceSubSequenceEvaluatorHolder {

        private static final SequenceSubSequenceEvaluator INSTANCE = new SequenceSubSequenceEvaluator();
    }
}
