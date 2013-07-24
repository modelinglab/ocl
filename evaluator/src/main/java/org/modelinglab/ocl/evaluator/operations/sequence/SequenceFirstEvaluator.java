/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.First;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.ListFirst;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceFirstEvaluator extends OperationEvaluator {
    
    private SequenceFirstEvaluator() {
    }
    
    public static SequenceFirstEvaluator getInstance() {
        return SequenceFirstEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return First.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return ListFirst.visit(val.getValue(), arg);
    }
    
    private static class SequenceFirstEvaluatorHolder {

        private static final SequenceFirstEvaluator INSTANCE = new SequenceFirstEvaluator();
    }
}
