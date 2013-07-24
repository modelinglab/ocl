/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.walker;

import org.modelinglab.ocl.core.ast.Operation;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class IllegalOperationException extends RuntimeException {
    final Operation op;

    public IllegalOperationException(Operation op) {
        this.op = op;
    }

    public IllegalOperationException(Operation op, String message) {
        super(message);
        this.op = op;
    }

    public IllegalOperationException(Operation op, Throwable cause) {
        super(cause);
        this.op = op;
    }
    
}
