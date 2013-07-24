/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class NotOverridenOperationException extends AssertionError {
    private static final long serialVersionUID = 1L;

    public NotOverridenOperationException(Operation op) {
        super(op + " should have been overrided and concrete operation should have been executed!");
    }
}
