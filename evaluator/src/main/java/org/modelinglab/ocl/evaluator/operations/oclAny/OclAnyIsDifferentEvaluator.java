/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.core.standard.operations.oclAny.IsDifferent;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.utils.GenericEqual;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclAnyIsDifferentEvaluator extends AnyOperatorEvaluatorTemplate {
    private static final long serialVersionUID = 1L;

    public static OclAnyIsDifferentEvaluator getInstance() {
        return new OclAnyIsDifferentEvaluator();
    }

    @Override
    protected OclValue<?> evaluate(OclValue<?> val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        OclValue<?> isEqualVal = GenericEqual.equal(val, arg);
        if (isEqualVal.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        Boolean isEqual = (Boolean) isEqualVal.getValue();
        if (isEqual) {
            return BooleanValue.FALSE;
        }
        return BooleanValue.TRUE;
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal.getType().oclIsUndefined()) {
            if (argVal.getType().oclIsInvalid()) {
                return InvalidValue.instantiate();
            }
            return BooleanValue.FALSE;
        }
        return BooleanValue.TRUE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IsDifferent.getInstance();
    }
    
}
