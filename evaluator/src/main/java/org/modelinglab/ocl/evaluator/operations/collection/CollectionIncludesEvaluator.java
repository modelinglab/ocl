/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.Includes;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionIncludesEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionIncludesEvaluator() {
    }
    
    public static CollectionIncludesEvaluator getInstance() {
        return CollectionIncludesEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Includes.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return includes(val.getValue(), arg);
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return includes(val.getValue(), arg);
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return includes(val.getValue(), arg);
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return includes(val.getValue(), arg);
    }
    
    private OclValue<?> includes(List<? extends OclValue<?>> source, SwitchArgument arg) {
        OclValue<?> element = arg.arguments.get(0);
        
        if (element.getType().oclIsInvalid()) {
            return InvalidValue.instantiate();
        }
        
        if (source.contains(element)) {
            return BooleanValue.TRUE;
        }
        return BooleanValue.FALSE;
    }
    
    private static class CollectionIncludesEvaluatorHolder {

        private static final CollectionIncludesEvaluator INSTANCE = new CollectionIncludesEvaluator();
    }
}
