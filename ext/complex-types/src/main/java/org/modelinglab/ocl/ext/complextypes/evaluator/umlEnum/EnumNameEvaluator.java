/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.umlEnum;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.EnumValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.complextypes.operations.umlEnum.EnumName;

/**
 *
 */
public class EnumNameEvaluator extends OperationEvaluator {

    @Override
    public OclValue<?> visit(EnumValue val, OperationEvaluator.SwitchArgument arg) {
        return new StringValue(val.getValue().getName());
    }

    @Override
    public Operation getEvaluableOperation() {
        return EnumName.getInstance();
    }

    
}
