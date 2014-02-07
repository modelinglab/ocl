/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.string;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.complextypes.operations.string.StringSplit;

/**
 *
 */
public class StringSplitEvaluator extends OperationEvaluator {

    @Override
    public OclValue<?> visit(StringValue val, OperationEvaluator.SwitchArgument arg) {
        OclValue<?> arg1 = arg.arguments.get(0);
        if (arg1.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        assert arg1 instanceof StringValue;
        StringValue regexp = (StringValue) arg1;

        try {
            String[] slices = val.getValue().split(regexp.getValue());
            ArrayList<StringValue> slicesList = new ArrayList<>(slices.length);
            for (String slice : slices) {
                slicesList.add(new StringValue(slice));
            }
            
            return new SequenceValue<>(slicesList, PrimitiveType.STRING, true);         
        } catch (PatternSyntaxException ex) {
            return InvalidValue.instantiate();
        }
    }

    @Override
    public Operation getEvaluableOperation() {
        return StringSplit.getInstance();
    }

}
