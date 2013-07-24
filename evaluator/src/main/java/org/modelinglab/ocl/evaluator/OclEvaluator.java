/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator;

import javax.annotation.concurrent.ThreadSafe;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.vartables.IVariableTable;
import org.modelinglab.ocl.core.vartables.VariableTableException;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluationAlgorithmProvider;
import org.modelinglab.ocl.evaluator.walker.ExpressionWalker;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@ThreadSafe
public class OclEvaluator {
    
    IteratorEvaluationAlgorithmProvider itAlgorithmProvider;

    public OclEvaluator(IteratorEvaluationAlgorithmProvider itAlgorithmProvider) {
        this.itAlgorithmProvider = itAlgorithmProvider;
    }

    public OclValue<?> evaluate(OclExpression exp, UserEvaluationEnvironment env, IVariableTable varTable) throws OclEvaluationException, VariableTableException {
        env.getInstancesProvider().preExpression(exp, env);
        EvaluatorVisitorArg runtimeEnv = new EvaluatorVisitorArg(env, varTable, itAlgorithmProvider);
        try {
            OclValue<?> val = ExpressionWalker.getInstance().evaluate(exp, runtimeEnv);
            return val;
        }
        finally {
            env.getInstancesProvider().postExpression(exp, env);
        }
    }
    
 }
