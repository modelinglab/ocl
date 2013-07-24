/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.NaturalValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class RealOperationEvaluatorTemplate  extends OperationEvaluator {

    protected abstract OclValue<?> evaluate(OclValue<? extends Number> source, double sourceVal, SwitchArgument arg);
    
    @Override
    public OclValue<?> visit(IntegerValue val, OperationEvaluator.SwitchArgument arg) {
        return evaluate(val, val.getValue(), arg);
    }

    @Override
    public OclValue<?> visit(NaturalValue val, OperationEvaluator.SwitchArgument arg) {
        return evaluate(val, val.getValue().floatValue(), arg);
    }

    @Override
    public OclValue<?> visit(RealValue val, OperationEvaluator.SwitchArgument arg) {
        return evaluate(val, val.getValue(), arg);
    }

    protected final boolean isNumberValue(OclValue<?> val) {
        return val instanceof RealValue || val instanceof IntegerValue || val instanceof NaturalValue;
    }
}
