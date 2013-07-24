/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.integer;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.integer.Abs;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IntegerAbsEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(IntegerValue val, SwitchArgument arg) {
        assert arg.arguments.isEmpty();
        if (val.getValue() < 0) {
            return new IntegerValue(- val.getValue());
        }
        return val;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Abs.getInstance();
    }
    
}
