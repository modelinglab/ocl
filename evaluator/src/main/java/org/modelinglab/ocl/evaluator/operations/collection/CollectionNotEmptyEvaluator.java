/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.NotEmpty;
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
public class CollectionNotEmptyEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionNotEmptyEvaluator() {
    }
    
    public static CollectionNotEmptyEvaluator getInstance() {
        return CollectionNotEmptyEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return NotEmpty.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return notEmpty(val.getValue());
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return notEmpty(val.getValue());
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return notEmpty(val.getValue());
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return notEmpty(val.getValue());
    }
    
    private BooleanValue notEmpty(List<? extends OclValue<?>> list) {
        if (list.isEmpty()) {
            return BooleanValue.FALSE;
        }
        return BooleanValue.TRUE;
    }
    
    private static class CollectionNotEmptyEvaluatorHolder {

        private static final CollectionNotEmptyEvaluator INSTANCE = new CollectionNotEmptyEvaluator();
    }
}
