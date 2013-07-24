/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.AsSet;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.BagToSet;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceAsSetEvaluator extends OperationEvaluator {
    
    private SequenceAsSetEvaluator() {
    }
    
    public static SequenceAsSetEvaluator getInstance() {
        return SequenceAsSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return BagToSet.bagToSet(new BagValue<>(val), arg);
    }
    
    private static class SequenceAsSetEvaluatorHolder {

        private static final SequenceAsSetEvaluator INSTANCE = new SequenceAsSetEvaluator();
    }
}
