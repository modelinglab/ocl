/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.vartables;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class VariableAlreadyExistException extends VariableTableException {
    private static final long serialVersionUID = 1L;

    public VariableAlreadyExistException(String varName) {
        super("There is already a variable with " + varName + " id stored.");
    }
    
}
