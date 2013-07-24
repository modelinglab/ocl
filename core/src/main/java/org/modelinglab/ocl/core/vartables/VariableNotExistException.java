/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.vartables;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class VariableNotExistException extends VariableTableException {
    private static final long serialVersionUID = 1L;

    public VariableNotExistException(String varName) {
        super("There is not a variable with " + varName + " id stored.");
    }
    
}
