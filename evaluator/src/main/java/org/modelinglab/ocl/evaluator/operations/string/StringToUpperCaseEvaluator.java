/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.string.ToUpperCase;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class StringToUpperCaseEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(StringValue val, OperationEvaluator.SwitchArgument arg) {
        try {
            return new StringValue(val.getValue().toUpperCase());
        }
        catch (NumberFormatException ex) {
            return InvalidValue.instantiate();
        }
    }

    @Override
    public Operation getEvaluableOperation() {
        return ToUpperCase.getInstance();
    }
}
