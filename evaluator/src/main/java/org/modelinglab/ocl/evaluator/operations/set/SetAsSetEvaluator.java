/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.set.AsSet;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetAsSetEvaluator extends OperationEvaluator {
    
    private SetAsSetEvaluator() {
    }
    
    public static SetAsSetEvaluator getInstance() {
        return SetAsSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return val;
    }
    
    private static class SetAsSetEvaluatorHolder {

        private static final SetAsSetEvaluator INSTANCE = new SetAsSetEvaluator();
    }
}
