/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclVoid;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.oclVoid.IsEqual;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.GenericEqual;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclVoidIsEqualEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(InvalidValue val, SwitchArgument arg) {
        return GenericEqual.equal(val, arg);
        //TODO: check this
//        assert arg.arguments.size() == 1;
//        OclValue<?> argVal = arg.arguments.get(0);
//        
//        if (argVal instanceof VoidValue || argVal instanceof InvalidValue) {
//            return BooleanValue.TRUE;
//        }
//        return BooleanValue.FALSE;
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        return GenericEqual.equal(val, arg);
        //TODO: check this
//        assert arg.arguments.size() == 1;
//        OclValue<?> argVal = arg.arguments.get(0);
//        
//        if (argVal instanceof VoidValue || argVal instanceof InvalidValue) {
//            return BooleanValue.TRUE;
//        }
//        return BooleanValue.FALSE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IsEqual.getInstance();
    }
    
}
