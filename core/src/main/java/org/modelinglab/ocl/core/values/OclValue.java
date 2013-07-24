/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values;

import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.utils.ValueVisitor;
import java.io.Serializable;

/**
 *
 * @param <E> 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class OclValue<E> implements Serializable {

    private final E value;

    OclValue(E value) {
        this.value = value;
    }
    
    public abstract <Result, Arg> Result accept(ValueVisitor<Result, Arg> visitor, Arg arg);
    
    public abstract Classifier getType();
    
    @Override
    public abstract String toString();
    
    public E getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OclValue<?> other = (OclValue<?>) obj;
        if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }
}
