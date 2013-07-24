/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class FeatureCallExp extends CallExp {
    
    private boolean pre;

    public FeatureCallExp() {
    }

    protected FeatureCallExp(FeatureCallExp other) {
        super(other);
        setPre(other.isPre());
    }

    public boolean isPre() {
        return pre;
    }

    public final void setPre(boolean pre) {
        this.pre = pre;
    }

    protected boolean equalsFeatureCall(FeatureCallExp other) {
        return super.equalsCallExp(other) && this.pre == other.pre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (this.pre ? 1 : 0);
        return 19 * hash + super.hashCode();
    }

    abstract public boolean equals(Object obj);
    
}
