/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.operations.real;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.real.Round;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.NaturalValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 */
public class RealRoundEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(IntegerValue val, OperationEvaluator.SwitchArgument arg) {
        return val;
    }

    @Override
    public OclValue<?> visit(NaturalValue val, OperationEvaluator.SwitchArgument arg) {
        return val;
    }

    @Override
    public OclValue<?> visit(RealValue val, OperationEvaluator.SwitchArgument arg) {
        return new IntegerValue((long) Math.round(val.getValue()));
    }

    @Override
    public Operation getEvaluableOperation() {
        return Round.getInstance();
    }
    
}
