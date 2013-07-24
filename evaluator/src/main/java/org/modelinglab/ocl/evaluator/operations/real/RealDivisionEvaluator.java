/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.real.Division;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.core.values.VoidValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class RealDivisionEvaluator extends RealOperationEvaluatorTemplate {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected OclValue<?> evaluate(OclValue<? extends Number> source, double sourceVal, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (isNumberValue(argVal)) {
            double argFloatVal = ((Number) argVal.getValue()).doubleValue();
            if (argFloatVal == 0) { //as is specified in OCL 2.3.1, int / 0 returns invalid
                return InvalidValue.instantiate();
            }
            return new RealValue(sourceVal / argFloatVal); //is a real division!
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public Operation getEvaluableOperation() {
        return Division.getInstance();
    }
    
}