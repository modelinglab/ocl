/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator;

import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.values.ObjectValue;

/**
 *
 */
public class UnexpectedObjectException extends RuntimeException {

    private final ObjectValue<?> unexpectedValue;

    public UnexpectedObjectException(ObjectValue<?> unexpectedValue) {
        this.unexpectedValue = unexpectedValue;
    }

    public UnexpectedObjectException(ObjectValue<?> unexpectedValue, String message) {
        super(message);
        this.unexpectedValue = unexpectedValue;
    }

    public UnexpectedObjectException(ObjectValue<?> unexpectedValue, String message, Throwable cause) {
        super(message, cause);
        this.unexpectedValue = unexpectedValue;
    }

    public UnexpectedObjectException(ObjectValue<?> unexpectedValue, Throwable cause) {
        super(cause);
        this.unexpectedValue = unexpectedValue;
    }

    @Override
    public String getMessage() {
        String superMessage = super.getMessage();
        if (superMessage != null) {
            return superMessage;
        }
        return "There is no " + unexpectedValue.getType() + " with id " + unexpectedValue.getValue().getId();
    }
}
