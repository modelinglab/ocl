/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.string.Addition;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class StringAdditionEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(StringValue val, SwitchArgument arg) {
        return StringConcatEvaluator.concatenate(val, arg);
    }

    @Override
    public Operation getEvaluableOperation() {
        return Addition.getInstance();
    }
    
}
