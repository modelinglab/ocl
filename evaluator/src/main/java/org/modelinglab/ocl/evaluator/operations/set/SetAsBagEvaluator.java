/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.set.AsBag;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetAsBagEvaluator extends OperationEvaluator {
    
    private SetAsBagEvaluator() {
    }
    
    public static SetAsBagEvaluator getInstance() {
        return SetAsBagEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsBag.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return new BagValue<>(val);
    }
    
    private static class SetAsBagEvaluatorHolder {

        private static final SetAsBagEvaluator INSTANCE = new SetAsBagEvaluator();
    }
}
