/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

/**
 *
 * @param <T> 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class PrimitiveLiteralExp<T> extends LiteralExp {
    
    private T value;

    public PrimitiveLiteralExp() {
    }

    public PrimitiveLiteralExp(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PrimitiveLiteralExp)) {
            return false;
        }
        final PrimitiveLiteralExp<T> other = (PrimitiveLiteralExp<T>) obj;
        if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }
    
}
