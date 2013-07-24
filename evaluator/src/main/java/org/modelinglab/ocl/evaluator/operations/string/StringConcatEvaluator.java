/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.string.Concat;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class StringConcatEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    /**
     * This static function is used to be called from {@link StringAdditionEvaluator}, the evaluator
     * of String::+(s:String), operator which is an alias of String::concat(s:String)
     * @param val
     * @param arg
     * @return 
     */
    static OclValue<?> concatenate(StringValue val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        
        OclValue<?> argVal = arg.arguments.get(0);
        
        if (argVal instanceof StringValue) {
            return new StringValue(val.getValue() + (String) argVal.getValue());
        }
        assert argVal instanceof VoidValue || argVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }
    
    @Override
    public OclValue<?> visit(StringValue val, SwitchArgument arg) {
        return concatenate(val, arg);
    }

    @Override
    public Operation getEvaluableOperation() {
        return Concat.getInstance();
    }
    
}
