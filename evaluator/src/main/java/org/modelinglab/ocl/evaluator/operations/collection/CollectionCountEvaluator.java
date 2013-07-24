/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.Count;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.set.SetCountEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionCountEvaluator extends CollectionOperationAbstractEvaluator {

    private CollectionCountEvaluator() {
    }

    public static CollectionCountEvaluator getInstance() {
        return CollectionCountHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Count.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return SetCountEvaluator.getInstance().visit(new SetValue<>(val), arg);
    }

    private static class CollectionCountHolder {

        private static final CollectionCountEvaluator INSTANCE = new CollectionCountEvaluator();
    }
}
