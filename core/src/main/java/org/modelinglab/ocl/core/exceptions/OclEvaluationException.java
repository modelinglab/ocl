/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.exceptions;

import org.modelinglab.ocl.core.ast.expressions.OclExpression;

/**
 * This expression is throw when an OCL expression cannot be evaluated in the actual context. This can happen,
 * for exaple, when expression uses a variable or type that is not declared (or is different) in the 
 * evaluation environment.
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclEvaluationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final OclExpression expression;
    
    public OclEvaluationException(OclExpression expression) {
        this.expression = expression;
    }

    public OclEvaluationException(OclExpression expression, String message) {
        super(message);
        this.expression = expression;
    }

    public OclEvaluationException(OclExpression expression, String message, Throwable cause) {
        super(message, cause);
        this.expression = expression;
    }

    public OclEvaluationException(OclExpression expression, Throwable cause) {
        super(cause);
        this.expression = expression;
    }

    public OclExpression getExpression() {
        return expression;
    }

}
