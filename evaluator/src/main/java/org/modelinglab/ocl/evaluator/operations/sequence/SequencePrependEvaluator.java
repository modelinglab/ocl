/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.ArrayList;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.Prepend;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequencePrependEvaluator extends OperationEvaluator {
    
    private SequencePrependEvaluator() {
    }
    
    public static SequencePrependEvaluator getInstance() {
        return SequencePrependEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Prepend.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> element = arg.arguments.get(0);
        if (element instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }
        //TODO: gortiz: If we play with very big collections, a view could dramatically improve our performance.
        ArrayList<OclValue<?>> newResult = new ArrayList<>(val.getValue().size());
        newResult.add(element);
        newResult.addAll(val.getValue());
        
        return new SequenceValue<>(newResult, val.getType().getElementType(), true);
    }
    
    private static class SequencePrependEvaluatorHolder {

        private static final SequencePrependEvaluator INSTANCE = new SequencePrependEvaluator();
    }
}
