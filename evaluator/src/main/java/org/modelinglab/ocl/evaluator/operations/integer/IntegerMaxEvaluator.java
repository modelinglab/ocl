/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.integer;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.integer.Max;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IntegerMaxEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(IntegerValue val, OperationEvaluator.SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal instanceof IntegerValue) {
            if (val.getValue() >= ((Number) argVal.getValue()).longValue()) {
                return val;
            }
            return argVal;
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public Operation getEvaluableOperation() {
        return Max.getInstance();
    }
    
}