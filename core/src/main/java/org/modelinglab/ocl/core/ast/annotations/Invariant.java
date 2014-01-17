/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.annotations;

import java.io.Serializable;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;

/**
 *
 * @author Miguel Angel Garcia de Dios <miguelangel.garcia at imdea.org>
 */
public class Invariant implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private UmlClass context;
    private OclExpression expression;

    public Invariant() {
    }

    public Invariant(String name) {
        this.name = name;
    }

    public Invariant(String name, UmlClass context, OclExpression expression, String description) {
        this.name = name;
        this.context = context;
        this.expression = expression;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UmlClass getContext() {
        return context;
    }

    public void setContext(UmlClass context) {
        this.context = context;
    }

    public OclExpression getExpression() {
        return expression;
    }

    public void setExpression(OclExpression expression) {
        this.expression = expression;
    }
    
    
}
