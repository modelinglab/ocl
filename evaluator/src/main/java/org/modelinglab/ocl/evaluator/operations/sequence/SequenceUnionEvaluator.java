/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import java.util.ArrayList;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.Union;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceUnionEvaluator extends OperationEvaluator {
    
    private SequenceUnionEvaluator() {
    }
    
    public static SequenceUnionEvaluator getInstance() {
        return SequenceUnionEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Union.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal.getType().oclIsUndefined()) { //null or invalid
            return InvalidValue.instantiate();
        }
        assert argVal.getType().conformsTo(val.getType());
        @SuppressWarnings("unchecked")
        SequenceValue<OclValue<?>> argSeq = (SequenceValue<OclValue<?>>) argVal;
        
        /*
         * gortiz: this operation could be implemented in a more efficient way with multithread or an 
         * "list union view"
         */
        ArrayList<OclValue<?>> resultList = new ArrayList<>(val.getValue().size() + argSeq.getValue().size());
        resultList.addAll(val.getValue());
        resultList.addAll(argSeq.getValue());
        
        return new SequenceValue<>(resultList, val.getType().getElementType(), true);
    }
    
    private static class SequenceUnionEvaluatorHolder {

        private static final SequenceUnionEvaluator INSTANCE = new SequenceUnionEvaluator();
    }
}
