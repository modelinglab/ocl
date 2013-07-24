/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.IndexOf;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.ListIndexOf;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceIndexOfEvaluator extends OperationEvaluator {
    
    private SequenceIndexOfEvaluator() {
    }
    
    public static SequenceIndexOfEvaluator getInstance() {
        return SequenceIndexOfEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IndexOf.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return ListIndexOf.visit(val.getValue(), arg);
    }
    
    private static class SequenceIndexOfEvaluatorHolder {

        private static final SequenceIndexOfEvaluator INSTANCE = new SequenceIndexOfEvaluator();
    }
}
