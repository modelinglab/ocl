/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.bag.AsSet;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.BagToSet;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagAsSetEvaluator extends OperationEvaluator {
    
    private BagAsSetEvaluator() {
    }
    
    public static BagAsSetEvaluator getInstance() {
        return BagAsSetEvaluatorHolder.INSTANCE;
    }
    
    @Override
    public Operation getEvaluableOperation() {
        return AsSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, OperationEvaluator.SwitchArgument arg) {
        return BagToSet.bagToSet(val, arg);
    }
    
    private static class BagAsSetEvaluatorHolder {

        private static final BagAsSetEvaluator INSTANCE = new BagAsSetEvaluator();
    }
}
