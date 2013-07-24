/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.complextypes.operations.string.StringContains;

/**
 *
 */
public class StringContainsEvaluator extends OperationEvaluator {

    @Override
    public OclValue<?> visit(StringValue val, SwitchArgument arg) {
        OclValue<?> arg1 = arg.arguments.get(0);
        if (arg1.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        assert arg1 instanceof StringValue;
        StringValue subString = (StringValue) arg1;

        return BooleanValue.createValue(val.getValue().contains(subString.getValue()));
    }

    @Override
    public Operation getEvaluableOperation() {
        return StringContains.getInstance();
    }

}
