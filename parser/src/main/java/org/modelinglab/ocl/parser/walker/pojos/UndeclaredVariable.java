/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.pojos;

import org.modelinglab.ocl.core.ast.expressions.Variable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class UndeclaredVariable extends Variable {

    private static final long serialVersionUID = 1L;
    public static final String INVALID_VAR_NAME = "_private_var_";

    private UndeclaredVariable() {
        setName(INVALID_VAR_NAME);
    }

    public static UndeclaredVariable getInstance() {
        return UndeclaredVariableHolder.INSTANCE;
    }

    private static class UndeclaredVariableHolder {

        private static final UndeclaredVariable INSTANCE = new UndeclaredVariable();

        private UndeclaredVariableHolder() {
        }
    }
}
