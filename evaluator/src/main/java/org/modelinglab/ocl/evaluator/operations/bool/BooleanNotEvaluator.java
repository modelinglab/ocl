/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bool;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.bool.Not;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class BooleanNotEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;
    
    @Override
    public OclValue<?> visit(BooleanValue val, SwitchArgument arg) {
        assert arg.arguments.isEmpty();
        
        if (val.getValue()) { //not TRUE returns FALSE
            return BooleanValue.FALSE;
        }
        return BooleanValue.TRUE;
    }

    @Override
    public Operation getEvaluableOperation() {
        return Not.getInstance();
    }
}
