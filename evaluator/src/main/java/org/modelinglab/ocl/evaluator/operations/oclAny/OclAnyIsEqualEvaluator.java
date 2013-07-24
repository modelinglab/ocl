/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.core.standard.operations.oclAny.IsEqual;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.utils.GenericEqual;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclAnyIsEqualEvaluator extends AnyOperatorEvaluatorTemplate {
    private static final long serialVersionUID = 1L;

    public static OclAnyIsEqualEvaluator getInstance() {
        return new OclAnyIsEqualEvaluator();
    }
    
    @Override
    protected OclValue<?> evaluate(OclValue<?> val, SwitchArgument arg) {
        return GenericEqual.equal(val, arg);
        //TODO: check this
//        OclValue<?> argVal = arg.arguments.get(0);
//        if (argVal.getType().conformsTo(VoidType.getInstance())) {
//            return InvalidValue.instantiate();
//        }
//        if (val.equals(argVal)) {
//            return BooleanValue.TRUE;
//        }
//        return BooleanValue.FALSE;
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal.getType().oclIsUndefined()) {
            if (argVal.getType().oclIsInvalid()) {
                return InvalidValue.instantiate();
            }
            return BooleanValue.TRUE;
        }
        return BooleanValue.FALSE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IsEqual.getInstance();
    }
    
}
