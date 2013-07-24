/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.bag.Count;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.sequence.SequenceCountEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagCountEvaluator extends OperationEvaluator {
    
    private BagCountEvaluator() {
    }
    
    public static BagCountEvaluator getInstance() {
        return BagCountEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Count.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return SequenceCountEvaluator.getInstance().visit(new SequenceValue<>(val), arg);
    }
    
    private static class BagCountEvaluatorHolder {

        private static final BagCountEvaluator INSTANCE = new BagCountEvaluator();
    }
}
