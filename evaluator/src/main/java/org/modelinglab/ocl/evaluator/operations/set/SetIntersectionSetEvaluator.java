/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.standard.operations.set.IntersectionSet;
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
public class SetIntersectionSetEvaluator extends OperationEvaluator {
    
    private SetIntersectionSetEvaluator() {
    }
    
    public static SetIntersectionSetEvaluator getInstance() {
        return SetIntersectionSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IntersectionSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal instanceof SetValue) {
            SetValue<? extends OclValue<?>> setArgVal = (SetValue<? extends OclValue<?>>) argVal;
            
            assert arg.exp.getType() instanceof SetType;
            SetType resultType = (SetType) arg.exp.getType();
            
            return Intersection.setIntersection(setArgVal, val, resultType.getElementType());
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public boolean isImplemented() {
        return true;
    }
    
    private static class SetIntersectionSetEvaluatorHolder {

        private static final SetIntersectionSetEvaluator INSTANCE = new SetIntersectionSetEvaluator();
    }
}
