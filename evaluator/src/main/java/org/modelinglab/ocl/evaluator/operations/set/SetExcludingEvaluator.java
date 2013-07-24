/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import java.util.ArrayList;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.set.Excluding;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.sequence.SequenceExcludingEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetExcludingEvaluator extends OperationEvaluator {
    
    private SetExcludingEvaluator() {
    }
    
    public static SetExcludingEvaluator getInstance() {
        return SetExcludingEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Excluding.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> element = arg.arguments.get(0);
        
        if (element instanceof InvalidValue) {
            return InvalidValue.instantiate();
        }
        
        List<? extends OclValue<?>> tempList = new ArrayList<>(val.getValue());
        
        tempList.remove(element);
        assert !tempList.contains(element);
        
        return new SetValue<>(tempList, val.getType().getElementType(), true);
    }
    
    private static class SetExcludingEvaluatorHolder {

        private static final SetExcludingEvaluator INSTANCE = new SetExcludingEvaluator();
    }
}
