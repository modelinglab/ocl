/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.real.Abs;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class RealAbsEvaluator extends RealOperationEvaluatorTemplate {
    private static final long serialVersionUID = 1L;

    @Override
    protected OclValue<?> evaluate(OclValue<? extends Number> source, double sourceVal, SwitchArgument arg) {
        assert arg.arguments.isEmpty();
        if (sourceVal < 0f) {
            return new RealValue(- sourceVal);
        }
        return source;
    }
    
    @Override
    public Operation getEvaluableOperation() {
        return Abs.getInstance();
    }
    
}
