/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import java.util.ArrayList;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.set.Including;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetIncludingEvaluator extends OperationEvaluator {
    
    private SetIncludingEvaluator() {
    }
    
    public static SetIncludingEvaluator getInstance() {
        return SetIncludingEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Including.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> element = arg.arguments.get(0);
        
        if (element instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }
        
        if (val.getValue().contains(element)) { //element is already included, nothing to do
            return val;
        }
        
        List<OclValue<?>> tempList = new ArrayList<>(val.getValue().size() + 1);
        tempList.addAll(val.getValue());
        tempList.add(element);
        
        return new SetValue<>(tempList, val.getType().getElementType(), true);
    }
    
    private static class SetIncludingEvaluatorHolder {

        private static final SetIncludingEvaluator INSTANCE = new SetIncludingEvaluator();
    }
}
