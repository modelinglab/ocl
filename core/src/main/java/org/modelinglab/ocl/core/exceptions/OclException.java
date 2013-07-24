/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.exceptions;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclException extends Exception {
    private static final long serialVersionUID = 1L;


    public OclException(Throwable cause) {
        super(cause);
    }

    public OclException(String message, Throwable cause) {
        super(message, cause);
    }

    public OclException(String message) {
        super(message);
    }

    public OclException() {
    }
    
}
