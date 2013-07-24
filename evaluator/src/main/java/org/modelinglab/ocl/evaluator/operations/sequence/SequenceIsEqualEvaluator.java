/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.sequence.IsEqual;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.oclAny.OclAnyIsEqualEvaluator;
import org.modelinglab.ocl.evaluator.operations.utils.GenericEqual;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceIsEqualEvaluator extends OperationEvaluator {
    
    private SequenceIsEqualEvaluator() {
    }
    
    public static SequenceIsEqualEvaluator getInstance() {
        return SequenceIsEqualEvaluatorHolder.INSTANCE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return IsEqual.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument arg) {
        //gortiz: A multithread implementation could improve throughput when val.getValue().size() is big
        return GenericEqual.equal(val, arg);
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        return OclAnyIsEqualEvaluator.getInstance().visit(val, arg);
    }
    
    private static class SequenceIsEqualEvaluatorHolder {

        private static final SequenceIsEqualEvaluator INSTANCE = new SequenceIsEqualEvaluator();
    }
}
