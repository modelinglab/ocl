/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.exceptions;

import org.modelinglab.ocl.core.ast.expressions.AttributeCallExp;
import org.modelinglab.ocl.core.ast.expressions.CollectionLiteralPart;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;

/**
 * This exception is thrown when an OCL expression is illegal. In other words, when the expression does not
 * conform with its own AST invarians or preconditions. For example, an {@link AttributeCallExp} which
 * source does not conform with its attribute class owner.
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IllegalOclExpression extends OclRuntimeException {
    private static final long serialVersionUID = 1L;
    
    private final OclExpression invalidExpression;
    private final CollectionLiteralPart invalidCollectionPart;

    public IllegalOclExpression(OclExpression invalidExpression) {
        this.invalidExpression = invalidExpression;
        this.invalidCollectionPart = null;
    }

    public IllegalOclExpression(OclExpression invalidExpression, String message) {
        super(message);
        this.invalidExpression = invalidExpression;
        this.invalidCollectionPart = null;
    }

    public IllegalOclExpression(OclExpression invalidExpression, String message, Throwable cause) {
        super(message, cause);
        this.invalidExpression = invalidExpression;
        this.invalidCollectionPart = null;
    }

    public IllegalOclExpression(OclExpression invalidExpression, Throwable cause) {
        super(cause);
        this.invalidExpression = invalidExpression;
        this.invalidCollectionPart = null;
    }

    public IllegalOclExpression(CollectionLiteralPart invalidCollectionPart) {
        this.invalidCollectionPart = invalidCollectionPart;
        this.invalidExpression = null;
    }

    public IllegalOclExpression(CollectionLiteralPart invalidCollectionPart, String message) {
        super(message);
        this.invalidCollectionPart = invalidCollectionPart;
        this.invalidExpression = null;
    }

    public IllegalOclExpression(CollectionLiteralPart invalidCollectionPart, String message, Throwable cause) {
        super(message, cause);
        this.invalidCollectionPart = invalidCollectionPart;
        this.invalidExpression = null;
    }

    public IllegalOclExpression(CollectionLiteralPart invalidCollectionPart, Throwable cause) {
        super(cause);
        this.invalidCollectionPart = invalidCollectionPart;
        this.invalidExpression = null;
    }

    public CollectionLiteralPart getInvalidCollectionPart() {
        return invalidCollectionPart;
    }

    public OclExpression getInvalidExpression() {
        return invalidExpression;
    }
    
}
