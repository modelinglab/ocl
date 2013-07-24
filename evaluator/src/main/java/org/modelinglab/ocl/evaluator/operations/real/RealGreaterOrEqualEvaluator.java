/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.real.GreaterOrEqual;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class RealGreaterOrEqualEvaluator extends RealOperationEvaluatorTemplate {

    private static final long serialVersionUID = 1L;

    @Override
    protected OclValue<?> evaluate(OclValue<? extends Number> source, double sourceVal, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        OclValue<?> val = arg.arguments.get(0);
        
        if (val.getValue() instanceof Number) {
            double argVal = ((Number) val.getValue()).doubleValue();
            if (sourceVal >= argVal) {
                return BooleanValue.TRUE;
            }
            return BooleanValue.FALSE;
        }
        assert val instanceof VoidValue || val instanceof InvalidValue;
        return InvalidValue.instantiate();        
    }

    @Override
    public Operation getEvaluableOperation() {
        return GreaterOrEqual.getInstance();
    }
}

