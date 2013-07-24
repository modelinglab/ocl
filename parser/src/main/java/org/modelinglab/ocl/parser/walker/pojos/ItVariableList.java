/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.pojos;

import org.modelinglab.ocl.core.ast.expressions.Variable;
import java.util.ArrayList;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ItVariableList extends ArrayList<Variable> {
    private static final long serialVersionUID = 1L;

    public ItVariableList(int initialCapacity) {
        super(initialCapacity);
    }
    
    public ItVariableList clone() {
        ItVariableList clone = new ItVariableList(this.size());
        for (Variable variable : this) {
            clone.add(variable.clone());
        }
        return clone;
    }
    
}
