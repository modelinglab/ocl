/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.set.AsOrderedSet;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetAsOrderedSetEvaluator extends OperationEvaluator {
    
    private SetAsOrderedSetEvaluator() {
    }
    
    public static SetAsOrderedSetEvaluator getInstance() {
        return SetAsOrderedSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsOrderedSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return new OrderedSetValue<>(val);
    }
    
    private static class SetAsOrderedSetEvaluatorHolder {

        private static final SetAsOrderedSetEvaluator INSTANCE = new SetAsOrderedSetEvaluator();
    }
}
