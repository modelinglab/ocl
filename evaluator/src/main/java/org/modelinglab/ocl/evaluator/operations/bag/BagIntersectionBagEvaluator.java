/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.standard.operations.bag.IntersectionBag;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.Intersection;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagIntersectionBagEvaluator extends OperationEvaluator {
    
    private BagIntersectionBagEvaluator() {
    }
    
    public static BagIntersectionBagEvaluator getInstance() {
        return BagIntersectionBagEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IntersectionBag.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal instanceof BagValue) {
            BagValue<? extends OclValue<?>> bagArgVal = (BagValue<? extends OclValue<?>>) argVal;
            
            assert arg.exp.getType() instanceof BagType;
            BagType resultType = (BagType) arg.exp.getType();
            
            return Intersection.bagIntersection(val, bagArgVal, resultType.getElementType());
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public boolean isImplemented() {
        return true;
    }
    
    private static class BagIntersectionBagEvaluatorHolder {

        private static final BagIntersectionBagEvaluator INSTANCE = new BagIntersectionBagEvaluator();
    }
}
