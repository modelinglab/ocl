/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.annotations;

import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;

/**
 *
 * @author Miguel Angel Garcia de Dios <miguelangel.garcia at imdea.org>
 */
public class Invariant {
    private String name;
    private UmlClass context;
    private OclExpression expression;

    public Invariant() {
    }

    public Invariant(String name) {
        this.name = name;
    }

    public Invariant(String name, UmlClass context, OclExpression expression) {
        this.name = name;
        this.context = context;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
