/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.string.GreaterOrEqual;
import org.modelinglab.ocl.core.values.*;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class StringGreaterOrEqualEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(StringValue val, OperationEvaluator.SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal instanceof StringValue) {
            String otherString = (String) argVal.getValue();
            if (val.getValue().compareTo(otherString) >= 0) {
                return BooleanValue.TRUE;
            }
            return BooleanValue.FALSE;
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();        
    }

    @Override
    public Operation getEvaluableOperation() {
        return GreaterOrEqual.getInstance();
    }
}
