/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.AsSequence;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceAsSequenceEvaluator extends OperationEvaluator {
    
    private SequenceAsSequenceEvaluator() {
    }
    
    public static SequenceAsSequenceEvaluator getInstance() {
        return SequenceAsSequenceEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsSequence.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return val;
    }
    
    private static class SequenceAsSequenceEvaluatorHolder {

        private static final SequenceAsSequenceEvaluator INSTANCE = new SequenceAsSequenceEvaluator();
    }
}
