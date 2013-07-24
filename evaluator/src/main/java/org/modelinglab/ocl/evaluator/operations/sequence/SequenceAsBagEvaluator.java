/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.AsBag;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceAsBagEvaluator extends OperationEvaluator{
    
    private SequenceAsBagEvaluator() {
    }
    
    public static SequenceAsBagEvaluator getInstance() {
        return SequenceAsBagEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsBag.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return new BagValue<>(val);
    }
    
    private static class SequenceAsBagEvaluatorHolder {

        private static final SequenceAsBagEvaluator INSTANCE = new SequenceAsBagEvaluator();
    }
}
