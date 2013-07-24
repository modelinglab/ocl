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
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class Attribute extends Property {
    private static final long serialVersionUID = 1L;
    private Classifier referredType;
    
    public Attribute() {}
    
    /**
     * This copy constructor only copy basic attributes like arity and name
     * @param original 
     */
    public Attribute(Attribute original) {
        super(original);
    }
    
    @Nullable
    @Override
    public Classifier getReferredType() {
        return referredType;
    }

    public void setReferredType(Classifier referredType) {
        modifyAttempt();
        this.referredType = referredType;
    }
    
    public void setUmlClass(UmlClass umlClass) {
        modifyAttempt();
        if (getUmlClass() != null) {
            getUmlClass().removeOwnedAttributePrivate(this);
        }
        setUmlClassPrivate(umlClass);
        if (getUmlClass() != null) {
            getUmlClass().addOwnedAttributePrivate(this);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Attribute)) {
            return false;
        }
        return propertyEquals((Attribute) obj);
    }
    
    public <Result, Arg> Result accept(UmlVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
