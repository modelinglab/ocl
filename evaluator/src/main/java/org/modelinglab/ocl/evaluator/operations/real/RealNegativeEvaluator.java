/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.real.Negative;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.NaturalValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class RealNegativeEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue visit(NaturalValue val, SwitchArgument arg) {
        assert arg.arguments.isEmpty();
        return new NaturalValue(val.getValue().negate());
    }

    @Override
    public OclValue visit(RealValue val, SwitchArgument arg) {
        assert arg.arguments.isEmpty();
        if (val.getValue() == 0d) { //in java there are 0 and -0, which are different
            return val;
        }
        return new RealValue(- val.getValue());
    }

    @Override
    public OclValue visit(IntegerValue val, OperationEvaluator.SwitchArgument arg) {
        assert arg.arguments.isEmpty();
        return new IntegerValue(- val.getValue());
    }

    @Override
    public Operation getEvaluableOperation() {
        return Negative.getInstance();
    }
    
}
