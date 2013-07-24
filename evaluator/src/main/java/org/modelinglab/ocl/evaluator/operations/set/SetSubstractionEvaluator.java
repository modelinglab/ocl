/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import java.util.ArrayList;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.set.Substraction;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetSubstractionEvaluator extends OperationEvaluator {
    
    private SetSubstractionEvaluator() {
    }
    
    public static SetSubstractionEvaluator getInstance() {
        return SetSubstractionEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Substraction.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }
        if (argVal.getType().oclIsUndefined()) {
            /*
             *ocl 2.3.1 spec uses implicit asSet conversion, so substract  null shoud return the same set
             */
            return val;
        }
        @SuppressWarnings("unchecked")
        List<OclValue<?>> argAsList = (List<OclValue<?>>) argVal.getValue();
        if (argAsList.isEmpty()) {
            return val;
        }
        
        List<OclValue<?>> result = new ArrayList<>(val.getValue());
        result.removeAll(argAsList);
        
        return new SetValue<>(result, val.getType().getElementType(), true);
    }
    
    private static class SetSubstractionEvaluatorHolder {

        private static final SetSubstractionEvaluator INSTANCE = new SetSubstractionEvaluator();
    }
}
