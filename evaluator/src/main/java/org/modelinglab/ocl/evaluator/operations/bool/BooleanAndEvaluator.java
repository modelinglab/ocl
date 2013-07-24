/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bool;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.bool.And;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class BooleanAndEvaluator extends OperationEvaluator {

    private static final long serialVersionUID = 1L;
    
    @Override
    public OclValue<?> visit(BooleanValue val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal.getType().oclIsInvalid()) {
            return InvalidValue.instantiate();
        }
        
        if (!val.getValue()) { //FALSE and X returns FALSE
            return val;
        }
        if (argVal.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        //TRUE and X returns X
        assert argVal instanceof BooleanValue;
        return argVal;
    }

    @Override
    public OclValue<?> visit(InvalidValue val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
//        OclValue<?> argVal = arg.arguments.get(0);
//        
//        if(argVal.equals(BooleanValue.FALSE)) { //X and FALSE returns FALSE
//            return argVal;
//        }
        return val;
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        OclValue<?> argVal = arg.arguments.get(0);
        
        if(argVal.equals(BooleanValue.FALSE)) { //X and FALSE returns FALSE
            return argVal;
        }
        return InvalidValue.instantiate();
    }
    
    @Override
    public Operation getEvaluableOperation() {
        return And.getInstance();
    }
}
