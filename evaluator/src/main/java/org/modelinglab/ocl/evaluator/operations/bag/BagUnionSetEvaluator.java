/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.standard.operations.bag.UnionSet;
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
public class BagUnionSetEvaluator extends OperationEvaluator {
    
    private BagUnionSetEvaluator() {
    }
    
    public static BagUnionSetEvaluator getInstance() {
        return BagUnionSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return UnionSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, OperationEvaluator.SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal instanceof SetValue) {
            SetValue<?> setArgValue = (SetValue<?>) argVal;
            
            assert arg.exp.getType() instanceof BagType;
            BagType resultType = (BagType) arg.exp.getType();
            
            return Union.bagUnion(val, new BagValue<>(setArgValue), resultType.getElementType());
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public boolean isImplemented() {
        return true;
    }
    
    private static class BagUnionSetEvaluatorHolder {

        private static final BagUnionSetEvaluator INSTANCE = new BagUnionSetEvaluator();
    }
}
