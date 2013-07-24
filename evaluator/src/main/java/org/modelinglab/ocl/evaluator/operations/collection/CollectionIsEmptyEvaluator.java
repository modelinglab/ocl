/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.IsEmpty;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionIsEmptyEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionIsEmptyEvaluator() {
    }
    
    public static CollectionIsEmptyEvaluator getInstance() {
        return CollectionIsEmptyEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IsEmpty.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return isEmpty(val.getValue());
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return isEmpty(val.getValue());
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return isEmpty(val.getValue());
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return isEmpty(val.getValue());
    }
    
    private BooleanValue isEmpty(List<? extends OclValue<?>> list) {
        if (list.isEmpty()) {
            return BooleanValue.TRUE;
        }
        return BooleanValue.FALSE;
    }
    
    private static class CollectionIsEmptyEvaluatorHolder {

        private static final CollectionIsEmptyEvaluator INSTANCE = new CollectionIsEmptyEvaluator();
    }
}
