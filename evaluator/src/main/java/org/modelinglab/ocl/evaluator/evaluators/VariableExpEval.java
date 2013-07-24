/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.evaluators;

import org.modelinglab.ocl.core.ast.expressions.VariableExp;
import org.modelinglab.ocl.core.exceptions.IllegalOclExpression;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.utils.ValueDFW;
import org.modelinglab.ocl.core.vartables.VariableNotExistException;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;

/**
 *
 */
public class VariableExpEval {
    
    private static LookForNonexistentObjects lookForNonexistentObjectsDFW = new LookForNonexistentObjects();
    
    private VariableExpEval() {
    }
    
    public static VariableExpEval getInstance() {
        return VariableExpEvalHolder.INSTANCE;
    }
    
    public OclValue<?> evaluate(VariableExp exp, EvaluatorVisitorArg arg) {
        OclValue<?> value;
        try {
            value = arg.getVarTable().getValue(exp.getReferredVariable().getName());
        } catch (VariableNotExistException ex) {
            throw new IllegalOclExpression(exp, ex);
        }
        value.accept(lookForNonexistentObjectsDFW, arg);
        
        arg.getUserEvalEnv().getAnalyzer().analyze(exp, value);
        
        return value;
    }
    
    private static class LookForNonexistentObjects extends ValueDFW<EvaluatorVisitorArg> {

        @Override
        public void postObjectValue(ObjectValue<?> val, EvaluatorVisitorArg arg) {
            arg.getUserEvalEnv().getInstancesProvider().checkObject(val);
        }
        
    }
    
    private static class VariableExpEvalHolder {

        private static final VariableExpEval INSTANCE = new VariableExpEval();
    }
}
