/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.ExcludesAll;
import org.modelinglab.ocl.core.standard.operations.collection.IncludesAll;
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
public class CollectionIncludesAllEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionIncludesAllEvaluator() {
    }
    
    public static CollectionIncludesAllEvaluator getInstance() {
        return CollectionIncludesAllEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IncludesAll.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return includesAll(val.getValue(), arg);
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return includesAll(val.getValue(), arg);
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return includesAll(val.getValue(), arg);
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return includesAll(val.getValue(), arg);
    }

    private OclValue<?> includesAll(List<? extends OclValue<?>> source, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);

        //TODO: check if we want to return invalid with null args
        if (argVal.getType().oclIsUndefined()) { //true for null and invalid
            return InvalidValue.instantiate();
        }
        
        @SuppressWarnings("unchecked")
        List<OclValue<?>> argAsList = (List<OclValue<?>>) argVal.getValue();
        
        for (final OclValue<?> oclValue : argAsList) {
            if (!source.contains(oclValue)) {
                return BooleanValue.FALSE;
            }
        }
        return BooleanValue.TRUE;
    }
    
    private static class CollectionIncludesAllEvaluatorHolder {

        private static final CollectionIncludesAllEvaluator INSTANCE = new CollectionIncludesAllEvaluator();
    }
}
