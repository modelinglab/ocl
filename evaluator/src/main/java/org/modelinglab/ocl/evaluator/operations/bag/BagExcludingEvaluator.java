/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.bag.AsBag;
import org.modelinglab.ocl.core.standard.operations.bag.Excluding;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.sequence.SequenceExcludingEvaluator;
import org.modelinglab.ocl.evaluator.operations.sequence.SequenceIncludingEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagExcludingEvaluator extends OperationEvaluator {
    
    private BagExcludingEvaluator() {
    }
    
    public static BagExcludingEvaluator getInstance() {
        return BagExcludingEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Excluding.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, OperationEvaluator.SwitchArgument arg) {
        OclValue<?> seqResult = SequenceExcludingEvaluator.getInstance().visit(new SequenceValue<>(val), arg);
        if(seqResult instanceof SequenceValue) {
            return new BagValue<>((SequenceValue<? extends OclValue<?>>) seqResult);
        }
        assert seqResult instanceof InvalidValue || seqResult instanceof VoidValue;
        return seqResult;
    }
    
    private static class BagExcludingEvaluatorHolder {

        private static final BagExcludingEvaluator INSTANCE = new BagExcludingEvaluator();
    }
}
