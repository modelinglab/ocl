/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.LinkedList;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.standard.operations.sequence.InsertAt;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceInsertAtEvaluator extends OperationEvaluator {
    
    private SequenceInsertAtEvaluator() {
    }
    
    public static SequenceInsertAtEvaluator getInstance() {
        return SequenceInsertAtEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return InsertAt.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> arg1 = arg.arguments.get(0);
        if (arg1 instanceof VoidValue || arg1 instanceof InvalidValue) { //index cannot be null!
            return InvalidValue.instantiate();
        }
        IntegerValue indexVal = (IntegerValue) arg1;
        if (indexVal.getValue() < 1) {
            return InvalidValue.instantiate();
        }
        if (indexVal.getValue() > val.getValue().size()) {
            return InvalidValue.instantiate();
        }
        if (indexVal.getValue() > Integer.MAX_VALUE) {
            throw new OclEvaluationException(
                    arg.exp, 
                    indexVal.getValue() + " is to big to be a a valid argument in "
                    + arg.exp.getReferredOperation() + " operation.");
        }
        int index = indexVal.getValue().intValue() - 1; //OCL indixes goes from 1 to N
        if (index > val.getValue().size()) {
            return InvalidValue.instantiate();
        }
        
        OclValue<?> toInsert = arg.arguments.get(1);
        if (toInsert instanceof InvalidValue) { //value can be null
            return InvalidValue.instantiate();
        }
        
        List<OclValue<?>> resultList = new LinkedList<>(val.getValue());
        resultList.add(index, toInsert);
        return new SequenceValue<>(resultList, val.getType().getElementType(), true);
    }
    
    private static class SequenceInsertAtEvaluatorHolder {

        private static final SequenceInsertAtEvaluator INSTANCE = new SequenceInsertAtEvaluator();
    }
}
