/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.set.AsSequence;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetAsSequenceEvaluator extends OperationEvaluator {
    
    private SetAsSequenceEvaluator() {
    }
    
    public static SetAsSequenceEvaluator getInstance() {
        return SetAsSequenceEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsSequence.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return new SequenceValue<>(val);
    }
    
    private static class SetAsSequenceEvaluatorHolder {

        private static final SetAsSequenceEvaluator INSTANCE = new SetAsSequenceEvaluator();
    }
}
