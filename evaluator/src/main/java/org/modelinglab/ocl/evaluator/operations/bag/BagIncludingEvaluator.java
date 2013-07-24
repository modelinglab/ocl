/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.bag.Including;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.sequence.SequenceIncludingEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagIncludingEvaluator extends OperationEvaluator {
    
    private BagIncludingEvaluator() {
    }
    
    public static BagIncludingEvaluator getInstance() {
        return BagIncludingEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Including.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, OperationEvaluator.SwitchArgument arg) {
        OclValue<?> seqResult = SequenceIncludingEvaluator.getInstance().visit(new SequenceValue<>(val), arg);
        if(seqResult instanceof SequenceValue) {
            return new BagValue<>((SequenceValue<? extends OclValue<?>>) seqResult);
        }
        assert seqResult instanceof InvalidValue || seqResult instanceof VoidValue;
        return seqResult;
    }
    
    private static class BagIncludingEvaluatorHolder {

        private static final BagIncludingEvaluator INSTANCE = new BagIncludingEvaluator();
    }
}
