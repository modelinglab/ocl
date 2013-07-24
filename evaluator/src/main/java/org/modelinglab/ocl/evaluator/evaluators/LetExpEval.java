/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.evaluators;

import org.modelinglab.ocl.core.ast.expressions.LetExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.vartables.VariableTableException;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.walker.ExpressionWalker;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class LetExpEval {

    private LetExpEval() {
    }

    public static LetExpEval getInstance() {
        return LetExpEvalHolder.INSTANCE;
    }

    public OclValue<?> evaluate(LetExp exp, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException, VariableTableException {
        /*
         * First we need the variable and its evaluation value
         */
        Variable var = exp.getVariable();
        if (var.getInitExpression() == null) {
            throw new OclEvaluationException(exp, "Let variable " + var.getName() + " needs an init expression.");
        }
        OclValue<?> varValue = var.getInitExpression().accept(ExpressionWalker.getInstance(), runtimeEnv);
        if (!varValue.getType().conformsTo(var.getType())) {
            throw new OclEvaluationException(exp, var.getInitExpression() + " does not conforms to " + var.getType());
        }
        /*
         * Then we add this variable to the var table
         */
        runtimeEnv.getVarTable().createVariable(var, varValue);
        OclValue<?> result = null;
        try {
            result = exp.getIn().accept(ExpressionWalker.getInstance(), runtimeEnv);
        } finally {
            runtimeEnv.getVarTable().removeVariable(var.getName());
        }
        assert result != null;
        
        runtimeEnv.getUserEvalEnv().getAnalyzer().analyze(exp, varValue, result);
        
        return result;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return LetExpEval.getInstance();
    }

    private static class LetExpEvalHolder {

        private static final LetExpEval INSTANCE = new LetExpEval();

        private LetExpEvalHolder() {
        }
    }
}
