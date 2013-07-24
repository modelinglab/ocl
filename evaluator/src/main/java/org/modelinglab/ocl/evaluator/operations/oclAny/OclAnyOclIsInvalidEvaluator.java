/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.oclAny.OclIsInvalid;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclAnyOclIsInvalidEvaluator extends AnyOperatorEvaluatorTemplate {
    private static final long serialVersionUID = 1L;

    @Override
    protected OclValue<?> evaluate(OclValue<?> val, SwitchArgument arg) {
        return BooleanValue.FALSE;
    }

    @Override
    public OclValue<?> visit(InvalidValue val, SwitchArgument arg) {
        return BooleanValue.TRUE;
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        return BooleanValue.FALSE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return OclIsInvalid.getInstance();
    }
    
}
