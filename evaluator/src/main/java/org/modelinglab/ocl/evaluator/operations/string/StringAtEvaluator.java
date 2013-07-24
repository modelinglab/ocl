/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.string.At;
import org.modelinglab.ocl.core.values.*;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class StringAtEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(StringValue val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal instanceof IntegerValue) {
            long index = ((IntegerValue) argVal).getValue();
            index--; //indexes in OCL go from 1 to N
            String str = val.getValue();
            if (index < 0 || index >= str.length()) {
                return InvalidValue.instantiate();
            }
            return new StringValue(Character.toString(str.charAt((int) index)));
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public Operation getEvaluableOperation() {
        return At.getInstance();
    }
    
}
