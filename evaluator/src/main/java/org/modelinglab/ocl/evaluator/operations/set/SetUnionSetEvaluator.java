/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.standard.operations.set.UnionSet;
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
public class SetUnionSetEvaluator extends OperationEvaluator {

    private SetUnionSetEvaluator() {
    }

    public static SetUnionSetEvaluator getInstance() {
        return SetUnionSetEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return UnionSet.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;

        OclValue<?> argVal = arg.arguments.get(0);

        if (argVal instanceof SetValue) {
            SetValue<?> setArgVal = (SetValue<?>) argVal;
            
            assert arg.exp.getType() instanceof SetType;
            SetType resultType = (SetType) arg.exp.getType();
            
            return Union.setUnion(val, setArgVal, resultType.getElementType());
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public boolean isImplemented() {
        return true;
    }

    private static class SetUnionSetEvaluatorHolder {

        private static final SetUnionSetEvaluator INSTANCE = new SetUnionSetEvaluator();
    }
}
