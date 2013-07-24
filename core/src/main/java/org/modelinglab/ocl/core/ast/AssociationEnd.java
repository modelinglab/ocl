/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.ast.utils.UmlVisitor;
import java.util.Collection;
import javax.annotation.Nullable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class AssociationEnd extends Property {

    private static final long serialVersionUID = 1L;
    AssociationEnd opposited;

    public AssociationEnd() {
    }
    
    /**
     * This copy constructor only copy basic attributes like arity and name
     * @param original 
     */
    public AssociationEnd(AssociationEnd original) {
        super(original);
    }

    @Nullable
    @Override
    public UmlClass getReferredType() {
        if (opposited == null) {
            return null;
        }
        if (opposited.getUmlClass() == null) {
            return null;
        }
        return opposited.getUmlClass();
    }

    /**
     * @return Two associationEnds asso1 and asso2 of two objects o1 and o2 (which may be the same
     * object) may be paired with each other so that o1.asso1 refers to o2 if and only if o2.asso2
     * refers to o1. In such a case asso1 is the opposite of asso2 and asso2 is the opposite of
     * asso1.
     */
    public AssociationEnd getOpposited() {
        return opposited;
    }

    public void setUmlClass(UmlClass umlClass) {
        modifyAttempt();
        if (getUmlClass() != null) {
            getUmlClass().removeOwnedAssociationEndPrivate(this);
        }
        setUmlClassPrivate(umlClass);
        if (getUmlClass() != null) {
            getUmlClass().addOwnedAssociationEndPrivate(this);
        }
    }

    public void setOpposited(AssociationEnd opposited) {
        notifyUnset();
        setOppositedUnsecure(opposited);
        notifySet();
    }

    public void setOppositedUnsecure(AssociationEnd opposited) {
        modifyAttempt();
        this.opposited = opposited;
    }

    private void notifyUnset() {
        if (opposited != null) {
            opposited.setOppositedUnsecure(null);
        }
    }

    private void notifySet() {
        if (opposited != null) {
            opposited.setOppositedUnsecure(this);
        }
    }

    @Deprecated
    @Override
    public void setType(Classifier type) {
        throw new UnsupportedOperationException("Association end type is bound by its multiplicity "
                + "and the opposited end.");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AssociationEnd)) {
            return false;
        }

        AssociationEnd other = (AssociationEnd) obj;

        if (this.opposited != other.opposited && (this.opposited == null || !this.opposited.privateNoRecursiveEquals(other.opposited))) {
            return false;
        }
        return propertyEquals(other);
    }

    private boolean privateNoRecursiveEquals(AssociationEnd other) {
        if (other == null) {
            return false;
        }
        return propertyEquals(other);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    
    public <Result, Arg> Result accept(UmlVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
