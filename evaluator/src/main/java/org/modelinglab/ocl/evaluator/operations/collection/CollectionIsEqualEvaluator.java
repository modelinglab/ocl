/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.collection.IsEqual;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.oclAny.OclAnyIsEqualEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.GenericEqual;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionIsEqualEvaluator extends CollectionOperationAbstractEvaluator {
    
    private CollectionIsEqualEvaluator() {
    }
    
    public static CollectionIsEqualEvaluator getInstance() {
        return CollectionIsEqualEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IsEqual.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        return GenericEqual.equal(val, arg);
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal.getType().conformsTo(val.getType())) {
            return super.visit(val, arg); //this should thrown an error
        }
        return OclAnyIsEqualEvaluator.getInstance().visit(val, arg);
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal.getType().conformsTo(val.getType())) {
            return super.visit(val, arg); //this should thrown an error
        }
        return OclAnyIsEqualEvaluator.getInstance().visit(val, arg);
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal.getType().conformsTo(val.getType())) {
            return super.visit(val, arg); //this should thrown an error
        }
        return OclAnyIsEqualEvaluator.getInstance().visit(val, arg);
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        return OclAnyIsEqualEvaluator.getInstance().visit(val, arg);
    }
    
    private static class CollectionIsEqualEvaluatorHolder {

        private static final CollectionIsEqualEvaluator INSTANCE = new CollectionIsEqualEvaluator();
    }
}
