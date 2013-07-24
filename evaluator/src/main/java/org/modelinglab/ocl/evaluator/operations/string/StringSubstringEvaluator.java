/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.string.Substring;
import org.modelinglab.ocl.core.values.*;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class StringSubstringEvaluator extends OperationEvaluator {

    @Override
    public OclValue<?> visit(StringValue val, SwitchArgument arg) {
        assert arg.arguments.size() == 2;
        
        OclValue<?> fromVal = arg.arguments.get(0);
        OclValue<?> toVal = arg.arguments.get(1);
        
        if (fromVal instanceof IntegerValue && toVal instanceof IntegerValue) {
            Long from = (Long) fromVal.getValue();
            //from goes from 1 to N
            from--; //now is adapted to Java indexes
            
            Long to = (Long) toVal.getValue();
            /*
             * to goes from 1 to N, but substring upper bound is inclusive in OCL and exclusive in 
             * Java so we do not need to decrement "to" variable
             */
            String sourceString = val.getValue();
            if (from < 0 || from > to || to > sourceString.length()) {
                return InvalidValue.instantiate();
            }
            
            String result = sourceString.substring(from.intValue(), to.intValue());
            return new StringValue(result);            
        }
        
        assert fromVal instanceof VoidValue || fromVal instanceof InvalidValue || toVal instanceof VoidValue || toVal instanceof InvalidValue;
        return InvalidValue.instantiate();
    }

    @Override
    public Operation getEvaluableOperation() {
        return Substring.getInstance();
    }
    
    
}
