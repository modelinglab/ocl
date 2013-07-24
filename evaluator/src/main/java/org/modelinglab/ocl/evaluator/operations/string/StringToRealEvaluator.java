/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.string.ToReal;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class StringToRealEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(StringValue val, OperationEvaluator.SwitchArgument arg) {
        try {
            String str = val.getValue();
            double value = Double.parseDouble(str);
            if (value == -0.0) {
                value = 0;
            }
            return new RealValue(value);
        }
        catch (NumberFormatException ex) {
            return InvalidValue.instantiate();
        }
    }

    @Override
    public Operation getEvaluableOperation() {
        return ToReal.getInstance();
    }
    
}
