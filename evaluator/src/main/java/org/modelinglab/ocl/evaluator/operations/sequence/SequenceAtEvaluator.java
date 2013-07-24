/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.At;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.ListAt;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceAtEvaluator extends OperationEvaluator {
    
    private SequenceAtEvaluator() {
    }
    
    public static SequenceAtEvaluator getInstance() {
        return SequenceAtEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return At.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return ListAt.visit(val.getValue(), arg);
    }
    
    private static class SequenceAtEvaluatorHolder {

        private static final SequenceAtEvaluator INSTANCE = new SequenceAtEvaluator();
    }
}
