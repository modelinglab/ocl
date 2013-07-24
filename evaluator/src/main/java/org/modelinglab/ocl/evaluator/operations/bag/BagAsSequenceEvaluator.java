/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.bag.AsSequence;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagAsSequenceEvaluator extends OperationEvaluator {
    
    private BagAsSequenceEvaluator() {
    }
    
    public static BagAsSequenceEvaluator getInstance() {
        return BagAsSequenceEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsSequence.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, OperationEvaluator.SwitchArgument arg) {
        return new SequenceValue<>(val);
    }
    
    
    private static class BagAsSequenceEvaluatorHolder {

        private static final BagAsSequenceEvaluator INSTANCE = new BagAsSequenceEvaluator();
    }
}
