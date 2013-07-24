/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.MultiplicityElement;
import org.modelinglab.ocl.core.ast.types.MultiplicityElementImpl;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.SetType;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class Property extends Element {

    private boolean readOnly;
    private final MultiplicityElementImpl multiplicity;
    private UmlClass umlClass;
    private Classifier finalType;

    public Property() {
        multiplicity = new MultiplicityElementImpl();
    }
    
    /**
     * This copy constructor only copy basic attributes like arity and name
     * @param original 
     */
    public Property(Property original) {
        super(original);
        this.multiplicity = original.multiplicity.clone();
    }
    
    
    @Nullable
    public abstract Classifier getReferredType();
    
    protected Classifier calculateType() {
        Classifier calculatedType;
        Classifier referredType = getReferredType();
        if (referredType == null) {
            return null;
        }
        
        if (isMultivalued()) {
            if (isUnique()) {
                if (isOrdered()) {
                    calculatedType = new OrderedSetType(referredType);
                }
                else {
                    calculatedType = new SetType(referredType);
                }
            }
            else {
                if (isOrdered()) {
                    calculatedType = new SequenceType(referredType);
                }
                else {
                    calculatedType = new BagType(referredType);
                }
            }
        }
        else {
            calculatedType = referredType;
        }        
        
        return calculatedType;
    }
    
    /**
     * @return If isReadOnly is true, the attribute may not be written to after initialization. The
     * default value is false.
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        modifyAttempt();
        this.readOnly = readOnly;
    }

    public Classifier getType() {
        return calculateType();
    }

    @Deprecated
    public void setType(Classifier type) {
        throw new UnsupportedOperationException("Association end type is bound by its referred type.");
    }

    /**
     * @return The class that owns the property, and of which the property is an attribute.
     */
    public UmlClass getUmlClass() {
        return umlClass;
    }

    protected void setUmlClassPrivate(UmlClass umlClass) {
        modifyAttempt();
        this.umlClass = umlClass;
    }

    public boolean isMultivalued() {
        return multiplicity.isMultivalued();
    }

    public boolean includesMultiplicity(MultiplicityElement m) {
        return multiplicity.includesMultiplicity(m);
    }

    public boolean includesCardinality(int c) {
        return multiplicity.includesCardinality(c);
    }

    public int getUpperBound() {
        return multiplicity.getUpperBound();
    }

    public int getLowerBound() {
        return multiplicity.getLowerBound();
    }

    public boolean isUnique() {
        return multiplicity.isUnique();
    }

    public boolean isOrdered() {
        return multiplicity.isOrdered();
    }

    public void setUpperBound(int upperBound) {
        modifyAttempt();
        multiplicity.setUpperBound(upperBound);
    }

    public void setUnique(boolean unique) {
        modifyAttempt();
        multiplicity.setUnique(unique);
    }

    public void setOrdered(boolean ordered) {
        modifyAttempt();
        multiplicity.setOrdered(ordered);
    }

    public void setLowerBound(int lowerBound) {
        modifyAttempt();
        multiplicity.setLowerBound(lowerBound);
    }

    protected boolean propertyEquals(Property other) {
        if (this.umlClass != other.umlClass && (this.umlClass == null || !this.umlClass.equals(other.umlClass))) {
            return false;
        }
        if (this.readOnly != other.readOnly) {
            return false;
        }
        assert this.multiplicity != null;
        assert other.multiplicity != null;
        if (!this.multiplicity.equals(other.multiplicity)) {
            return false;
        }
        Classifier thisType = this.getType();
        Classifier otherType = other.getType();
        if (thisType != otherType && (thisType == null || !thisType.equals(otherType))) {
            return false;
        }
        return elementEquals(other);
    }
}
