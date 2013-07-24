/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.bag.AsBag;
import org.modelinglab.ocl.core.standard.operations.bag.AsOrderedSet;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.BagToSet;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagAsOrderedSetEvaluator extends OperationEvaluator {
    
    private BagAsOrderedSetEvaluator() {
    }
    
    public static BagAsOrderedSetEvaluator getInstance() {
        return BagAsOrderedSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return AsOrderedSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, OperationEvaluator.SwitchArgument arg) {
        return new OrderedSetValue<>(BagToSet.bagToSet(val, arg));
    }
    
    private static class BagAsOrderedSetEvaluatorHolder {

        private static final BagAsOrderedSetEvaluator INSTANCE = new BagAsOrderedSetEvaluator();
    }
}
