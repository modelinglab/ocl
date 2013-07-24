/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.bag.AsBag;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagAsBagEvaluator extends OperationEvaluator {
    
    private BagAsBagEvaluator() {
    }
    
    public static BagAsBagEvaluator getInstance() {
        return BagAsBagEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsBag.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return val;
    }
    
    private static class BagAsBagEvaluatorHolder {

        private static final BagAsBagEvaluator INSTANCE = new BagAsBagEvaluator();
    }
}
