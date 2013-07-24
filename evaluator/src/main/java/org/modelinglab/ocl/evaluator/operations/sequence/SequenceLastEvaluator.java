/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.Last;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.ListLast;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceLastEvaluator extends OperationEvaluator {
    
    private SequenceLastEvaluator() {
    }
    
    public static SequenceLastEvaluator getInstance() {
        return SequenceLastEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Last.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return ListLast.listLast(val.getValue(), arg);
    }
    
    private static class SequenceLastEvaluatorHolder {

        private static final SequenceLastEvaluator INSTANCE = new SequenceLastEvaluator();
    }
}
