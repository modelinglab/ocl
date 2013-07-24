/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.iterators.exceptions;

import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.exceptions.OclException;

/**
 *
 * @author gortiz
 */
public class IllegalIteratorException extends OclException {
    private static final long serialVersionUID = 1L;
    private final String iteratorName;
    private final Classifier sourceType;
    private final Classifier bodyType;

    public IllegalIteratorException(String iteratorName, Classifier sourceType, Classifier bodyType, String message) {
        super(message);
        this.iteratorName = iteratorName;
        this.sourceType = sourceType;
        this.bodyType = bodyType;
    }

    public IllegalIteratorException(String iteratorName, Classifier sourceType, Classifier bodyType) {
        this.iteratorName = iteratorName;
        this.sourceType = sourceType;
        this.bodyType = bodyType;
    }
}
