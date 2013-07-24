/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.standard.operations.bag.UnionBag;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.Union;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagUnionBagEvaluator extends OperationEvaluator {
    
    private BagUnionBagEvaluator() {
    }
    
    public static BagUnionBagEvaluator getInstance() {
        return BagUnionBagEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return UnionBag.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, OperationEvaluator.SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal instanceof BagValue) {
            BagValue<?> bagArgVal = (BagValue<?>) argVal;
            
            assert arg.exp.getType() instanceof BagType;
            BagType resultType = (BagType) arg.exp.getType();
            
            return Union.bagUnion(val, bagArgVal, resultType.getElementType());
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public boolean isImplemented() {
        return true;
    }
    
    private static class BagUnionBagEvaluatorHolder {

        private static final BagUnionBagEvaluator INSTANCE = new BagUnionBagEvaluator();
    }
}
