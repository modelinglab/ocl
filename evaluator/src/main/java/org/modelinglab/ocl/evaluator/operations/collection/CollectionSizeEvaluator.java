/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.Size;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionSizeEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionSizeEvaluator() {
    }
    
    public static CollectionSizeEvaluator getInstance() {
        return CollectionSizeEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Size.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return size(val.getValue());
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return size(val.getValue());
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return size(val.getValue());
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return size(val.getValue());
    }
    
    private IntegerValue size(List<? extends OclValue<?>> list) {
        return new IntegerValue((long) list.size());
    }
    
    private static class CollectionSizeEvaluatorHolder {

        private static final CollectionSizeEvaluator INSTANCE = new CollectionSizeEvaluator();
    }
}
