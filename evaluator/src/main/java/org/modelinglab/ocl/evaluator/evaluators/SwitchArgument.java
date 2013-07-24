/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.evaluators;

import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
class SwitchArgument<Exp extends OclExpression> {
    final Exp expression;
    final EvaluatorVisitorArg env;

    public SwitchArgument(Exp expression, EvaluatorVisitorArg env) {
        this.expression = expression;
        this.env = env;
    }
    
}
