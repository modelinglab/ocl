/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.ArrayList;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.Excluding;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceExcludingEvaluator extends OperationEvaluator{
    
    private SequenceExcludingEvaluator() {
    }
    
    public static SequenceExcludingEvaluator getInstance() {
        return SequenceExcludingEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Excluding.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> element = arg.arguments.get(0);
        
        if (element instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }
        
        List<? extends OclValue<?>> tempList = new ArrayList<>(val.getValue());
        while(tempList.remove(element)) {
            //nothing to do. Eventually this while has to end because tempList is finite.
        }
        return new SequenceValue<>(tempList, val.getType().getElementType(), true);
    }
    
    private static class SequenceExcludingEvaluatorHolder {

        private static final SequenceExcludingEvaluator INSTANCE = new SequenceExcludingEvaluator();
    }
}
