/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.real.Floor;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.NaturalValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class RealFloorEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(IntegerValue val, SwitchArgument arg) {
        return val;
    }

    @Override
    public OclValue<?> visit(NaturalValue val, SwitchArgument arg) {
        return val;
    }

    @Override
    public OclValue<?> visit(RealValue val, SwitchArgument arg) {
        return new IntegerValue((long) Math.floor(val.getValue()));
    }

    @Override
    public Operation getEvaluableOperation() {
        return Floor.getInstance();
    }
    
}
