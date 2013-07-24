/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.set.SymmetricDifference;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetSymmetricDifferenceEvaluator extends OperationEvaluator {
    
    private SetSymmetricDifferenceEvaluator() {
    }
    
    public static SetSymmetricDifferenceEvaluator getInstance() {
        return SetSymmetricDifferenceEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return SymmetricDifference.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal.getType().oclIsUndefined()) { //true for null and invalid
            return InvalidValue.instantiate();
        }
        @SuppressWarnings("unchecked")
        List<OclValue<?>> argAsList = (List<OclValue<?>>) argVal.getValue();
        
        Set<OclValue<?>> mySet = new HashSet<>(val.getValue());
        Set<OclValue<?>> otherSet = new HashSet<>(argAsList);
        
        Set<OclValue<?>> resultView = Sets.symmetricDifference(mySet, otherSet);
        return new SetValue<>(resultView, val.getType().getElementType());
    }
    
    private static class SetSymmetricDifferenceEvaluatorHolder {

        private static final SetSymmetricDifferenceEvaluator INSTANCE = new SetSymmetricDifferenceEvaluator();
    }
}
