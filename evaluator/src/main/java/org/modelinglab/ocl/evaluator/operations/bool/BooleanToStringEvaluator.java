/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bool;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.bool.ToString;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 * TODO: Check if null.toString() (and invalid.toString()) returns a null value (invalid value) o 
 * a 'null' String value ('invalid' String value)
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class BooleanToStringEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(BooleanValue val, SwitchArgument arg) {
        return new StringValue(val.getValue().toString());
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        return new StringValue("null");
    }

    @Override
    public Operation getEvaluableOperation() {
        return ToString.getInstance();
    }
    
}
