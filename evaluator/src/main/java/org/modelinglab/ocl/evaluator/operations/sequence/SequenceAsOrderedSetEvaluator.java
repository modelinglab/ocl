/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.ArrayList;
import java.util.HashSet;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.utils.OrderedSet;
import org.modelinglab.ocl.core.standard.operations.sequence.AsOrderedSet;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceAsOrderedSetEvaluator extends OperationEvaluator {
    
    private SequenceAsOrderedSetEvaluator() {
    }
    
    public static SequenceAsOrderedSetEvaluator getInstance() {
        return SequenceAsOrderedSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsOrderedSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        ArrayList<OclValue<?>> newArray = new ArrayList<>(val.getValue().size());
        HashSet<OclValue<?>> alreadyAdd = new HashSet<>(val.getValue().size());
        for (final OclValue<?> oclValue : val.getValue()) {
            if (!alreadyAdd.contains(oclValue)) { //removing duplicateds
                alreadyAdd.add(oclValue);
                newArray.add(oclValue);
            }
        }
        return new OrderedSetValue<>(newArray, val.getType().getElementType(), true);
    }
    
    private static class SequenceAsOrderedSetEvaluatorHolder {

        private static final SequenceAsOrderedSetEvaluator INSTANCE = new SequenceAsOrderedSetEvaluator();
    }
}
