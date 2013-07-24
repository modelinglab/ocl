/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.standard.operations.set.UnionBag;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.Union;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetUnionBagEvaluator extends OperationEvaluator {
    
    private SetUnionBagEvaluator() {
    }
    
    public static SetUnionBagEvaluator getInstance() {
        return SetUnionBagEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return UnionBag.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal instanceof BagValue) {
            BagValue<?> bagArgVal = (BagValue<?>) argVal;
            
            assert arg.exp.getType() instanceof BagType;
            BagType resultType = (BagType) arg.exp.getType();
            
            return Union.bagUnion(new BagValue<>(val), bagArgVal, resultType.getElementType());
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public boolean isImplemented() {
        return true;
    }
    
    private static class SetUnionBagEvaluatorHolder {

        private static final SetUnionBagEvaluator INSTANCE = new SetUnionBagEvaluator();
    }
}
