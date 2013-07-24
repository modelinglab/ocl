/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.exceptions;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public OclRuntimeException(Throwable cause) {
        super(cause);
    }

    public OclRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OclRuntimeException(String message) {
        super(message);
    }

    public OclRuntimeException() {
    }
    
}
