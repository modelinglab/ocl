/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.umlClass;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.EnumValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.complextypes.operations.umlClass.ClassToString;

/**
 *
 */
public class ClassToStringEvaluator extends OperationEvaluator {

    @Override
    public OclValue<?> visit(ObjectValue<?> val, SwitchArgument arg) {
        return new StringValue(val.getValue().toString());
    }

    @Override
    public OclValue<?> visit(EnumValue val, SwitchArgument arg) {
        return new StringValue(val.getValue().toString());
    }

    @Override
    public Operation getEvaluableOperation() {
        return ClassToString.getInstance();
    }

}
