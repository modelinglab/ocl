/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import java.util.Objects;
import javax.annotation.concurrent.Immutable;
import org.modelinglab.ocl.core.values.OclValue;

/**
 * This class represents an UmlObject<Id> which contains its class, its id and no attribute
 *
 * @param <Id>
 */
@Immutable
public class ReferenceUmlObject<Id> implements UmlObject<Id> {
    private static final long serialVersionUID = 1L;
    private final UmlClass umlClass;
    private final Id id;

    public ReferenceUmlObject(UmlClass umlClass, Id id) {
        this.umlClass = umlClass;
        this.id = id;
    }

    @Override
    public UmlClass getUmlClass() {
        return umlClass;
    }

    @Override
    public Id getId() {
        return id;
    }

    @Override
    public OclValue<?> getProperty(String propertyName) throws IllegalArgumentException, UmlObject.NotInitializedProperty {
        throw new UmlObject.NotInitializedProperty(propertyName, umlClass);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReferenceUmlObject<Id> other = (ReferenceUmlObject<Id>) obj;
        if (!Objects.equals(this.umlClass, other.umlClass)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return umlClass + "<" + id + '>';
    }
    
}
