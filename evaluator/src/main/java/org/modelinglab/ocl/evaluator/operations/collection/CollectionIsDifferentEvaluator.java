/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.IsDifferent;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.bag.BagIsEqualEvaluator;
import org.modelinglab.ocl.evaluator.operations.oclAny.OclAnyIsDifferentEvaluator;
import org.modelinglab.ocl.evaluator.operations.oclAny.OclAnyIsEqualEvaluator;
import org.modelinglab.ocl.evaluator.operations.sequence.SequenceIsEqualEvaluator;
import org.modelinglab.ocl.evaluator.operations.set.SetIsEqualEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionIsDifferentEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionIsDifferentEvaluator() {
    }
    
    public static CollectionIsDifferentEvaluator getInstance() {
        return CollectionIsDifferentEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IsDifferent.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal.getType().conformsTo(val.getType())) {
            return super.visit(val, arg); //this should thrown an error
        }
        return isDifferent(BagIsEqualEvaluator.getInstance().visit(val, arg));
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return isDifferent(CollectionIsEqualEvaluator.getInstance().visit(val, arg));
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal.getType().conformsTo(val.getType())) {
            return super.visit(val, arg); //this should thrown an error
        }
        return isDifferent(SequenceIsEqualEvaluator.getInstance().visit(val, arg));
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal.getType().conformsTo(val.getType())) {
            return super.visit(val, arg); //this should thrown an error
        }
        return isDifferent(SetIsEqualEvaluator.getInstance().visit(val, arg));
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal.getType().conformsTo(val.getType())) {
            return super.visit(val, arg); //this should thrown an error
        }
        return OclAnyIsDifferentEvaluator.getInstance().visit(val, arg);
    }
    
    private OclValue<?> isDifferent(OclValue<?> isEqual) {
        if (isEqual.getType().oclIsUndefined()) {//true for null and invalid
            return InvalidValue.instantiate();
        }
        Boolean isEqualVal = (Boolean) isEqual.getValue();
        
        if (isEqualVal) {
            return BooleanValue.FALSE;
        }
        return BooleanValue.TRUE;
    }
    
    private static class CollectionIsDifferentEvaluatorHolder {

        private static final CollectionIsDifferentEvaluator INSTANCE = new CollectionIsDifferentEvaluator();
    }
}
