/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class MultiplicityElementImpl implements MultiplicityElement {
    private static final long serialVersionUID = 1L;
    
    int lowerBound;
    int upperBound;
    boolean unique;
    boolean ordered;

    public MultiplicityElementImpl() {
        lowerBound = 0;
        upperBound = 1;
        unique = true;
        ordered = false;
    }

    public MultiplicityElementImpl(MultiplicityElementImpl other) {
        lowerBound = other.lowerBound;
        upperBound = other.upperBound;
        unique = other.unique;
        ordered = other.ordered;
    }

    @Override
    public boolean includesCardinality(int c) {
        if (upperBound == MultiplicityElement.UNBOUND) {
            return c >= lowerBound;
        }
        return c >= lowerBound && c <= upperBound;
    }

    @Override
    public boolean includesMultiplicity(MultiplicityElement m) {
        if (m.getLowerBound() > this.getLowerBound()) {
            return false;
        }
        if (this.getUpperBound() == UNBOUND) {
            return true;
        }
        if (m.getUpperBound() == UNBOUND) { //m is x..* and this is y..z with z < * => this does not includes m
            return false;
        }
        return this.getUpperBound() >= m.getUpperBound();
    }

    @Override
    public boolean isMultivalued() {
        return upperBound > 1 || upperBound == MultiplicityElement.UNBOUND;
    }

    @Override
    public int getLowerBound() {
        return lowerBound;
    }

    @Override
    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    @Override
    public int getUpperBound() {
        return upperBound;
    }

    @Override
    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public boolean isOrdered() {
        return ordered;
    }

    @Override
    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    @Override
    public boolean isUnique() {
        return unique;
    }

    @Override
    public void setUnique(boolean unique) {
        this.unique = unique;
    }
    
    @Override
    public MultiplicityElementImpl clone() {
        return new MultiplicityElementImpl(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MultiplicityElementImpl other = (MultiplicityElementImpl) obj;
        if (this.lowerBound != other.lowerBound) {
            return false;
        }
        if (this.upperBound != other.upperBound) {
            return false;
        }
        if (this.unique != other.unique) {
            return false;
        }
        if (this.ordered != other.ordered) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.lowerBound;
        hash = 37 * hash + this.upperBound;
        hash = 37 * hash + (this.unique ? 1 : 0);
        hash = 37 * hash + (this.ordered ? 1 : 0);
        return hash;
    }

    public void correctnessCheck() throws IllegalStateException {
        Preconditions.checkState(this.lowerBound <= this.upperBound, "lower bound should be less or "
                + "equal than upper bound!");
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
