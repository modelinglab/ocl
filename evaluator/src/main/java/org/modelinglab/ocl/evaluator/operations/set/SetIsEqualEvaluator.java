/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.set.IsEqual;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.oclAny.OclAnyIsEqualEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.GenericEqual;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetIsEqualEvaluator extends OperationEvaluator {
    
    private SetIsEqualEvaluator() {
    }
    
    public static SetIsEqualEvaluator getInstance() {
        return SetIsEqualEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IsEqual.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument arg) {
        //gortiz: A multithread implementation could improve throughput when val.getValue().size() is big
        return GenericEqual.equal(val, arg);
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        return OclAnyIsEqualEvaluator.getInstance().visit(val, arg);
    }
    
    private static class SetIsEqualEvaluatorHolder {

        private static final SetIsEqualEvaluator INSTANCE = new SetIsEqualEvaluator();
    }
}
