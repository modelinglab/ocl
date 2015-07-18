/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.ArrayList;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.Append;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceAppendEvaluator extends OperationEvaluator {
    
    private SequenceAppendEvaluator() {
    }
    
    public static SequenceAppendEvaluator getInstance() {
        return SequenceAppendEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Append.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> element = arg.arguments.get(0);
        if (element instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }
        //TODO: gortiz: If we play with very big collections, a view could dramatically improve our performance.
        ArrayList<OclValue<?>> newResult = new ArrayList<>(val.getValue().size());
        newResult.addAll(val.getValue());
        newResult.add(element);
        
        return new SequenceValue<>(newResult, val.getType().getElementType(), true);
    }
    
    private static class SequenceAppendEvaluatorHolder {

        private static final SequenceAppendEvaluator INSTANCE = new SequenceAppendEvaluator();
    }
}
