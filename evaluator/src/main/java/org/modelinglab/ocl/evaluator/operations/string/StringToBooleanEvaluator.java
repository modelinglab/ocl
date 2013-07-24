/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.string.ToBoolean;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class StringToBooleanEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    private static final String TRUE_LITERAL = "true";
    
    @Override
    public OclValue<?> visit(StringValue val, SwitchArgument arg) {
        if (val.getValue().equals(TRUE_LITERAL)) {
            return BooleanValue.TRUE;
        }
        return BooleanValue.FALSE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return ToBoolean.getInstance();
    }
    
}
