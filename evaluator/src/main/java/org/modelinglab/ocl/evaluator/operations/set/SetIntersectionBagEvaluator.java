/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.standard.operations.set.IntersectionBag;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.Intersection;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetIntersectionBagEvaluator extends OperationEvaluator {
    
    private SetIntersectionBagEvaluator() {
    }
    
    public static SetIntersectionBagEvaluator getInstance() {
        return SetIntersectionBagEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IntersectionBag.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal instanceof BagValue) {
            BagValue<? extends OclValue<?>> bagArgVal = (BagValue<? extends OclValue<?>>) argVal;
            
            assert arg.exp.getType() instanceof SetType;
            SetType resultType = (SetType) arg.exp.getType();
            
            return Intersection.setIntersection(val, bagArgVal, resultType.getElementType());
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public boolean isImplemented() {
        return true;
    }
    
    private static class SetIntersectionBagEvaluatorHolder {

        private static final SetIntersectionBagEvaluator INSTANCE = new SetIntersectionBagEvaluator();
    }
}
