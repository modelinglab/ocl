/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bool;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.bool.Implies;
import org.modelinglab.ocl.core.standard.operations.bool.Xor;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class BooleanXorEvaluator extends OperationEvaluator {

    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(BooleanValue val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        OclValue<?> argVal = arg.arguments.get(0);

        if (argVal.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        
        if (!val.getValue()) { //FALSE xor X returns X
            return argVal;
        }
        //TRUE implies X
        if (argVal instanceof BooleanValue) { //TRUE xor X returns not X when X is boolean
            if ((Boolean) argVal.getValue()) { //TRUE xor TRUE returns FALSE
                return BooleanValue.FALSE;
            }
            return BooleanValue.TRUE; //TRUE xor FALSE returns TRUE
        }
        //INVALID and NULL cases. TRUE xor {INVALID, NULL} returns {INVALID, NULL}
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return argVal;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Xor.getInstance();
    }
}
