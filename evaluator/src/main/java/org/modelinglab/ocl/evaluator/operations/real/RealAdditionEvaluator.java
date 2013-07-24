/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.real.Addition;
import org.modelinglab.ocl.core.values.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class RealAdditionEvaluator extends RealOperationEvaluatorTemplate {
    private static final long serialVersionUID = 1L;

    @Override
    protected OclValue<?> evaluate(OclValue<? extends Number> source, double sourceVal, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal instanceof RealValue || argVal instanceof IntegerValue || argVal instanceof NaturalValue) {
            double argValue = ((Number) argVal.getValue()).doubleValue();
            return new RealValue(sourceVal + argValue);
        }
        
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public Operation getEvaluableOperation() {
        return Addition.getInstance();
    }
    
}
