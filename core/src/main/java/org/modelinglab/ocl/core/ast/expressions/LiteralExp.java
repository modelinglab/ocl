/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class LiteralExp extends OclExpression {

    public LiteralExp() {
    }

    public LiteralExp(OclExpression other) {
        super(other);
    }
    
    /**
     * Name of LiteralExp can not be set
     * @param name 
     */
    @Override
    @Deprecated
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
