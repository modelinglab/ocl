/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.Count;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceCountEvaluator extends OperationEvaluator {
    
    private SequenceCountEvaluator() {
    }
    
    public static SequenceCountEvaluator getInstance() {
        return SequenceCountEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Count.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> element = arg.arguments.get(0);
        if (element instanceof InvalidValue) { //null is a valid value!
            return InvalidValue.instantiate();
        }
        int occurrences = 0;
        for (final OclValue<?> oclValue : val.getValue()) {
            if (oclValue.equals(element)) {
                occurrences++;
            }
        }
        return new IntegerValue((long) occurrences);
    }
    
    private static class SequenceCountEvaluatorHolder {

        private static final SequenceCountEvaluator INSTANCE = new SequenceCountEvaluator();
    }
}
