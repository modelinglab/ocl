/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time;

import java.util.Objects;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 */
public class UmlWrapperObject<W> implements UmlObject<Long> {

    private final W wrappedObject;
    private final UmlClass umlClass;

    public UmlWrapperObject(W wrappedObject, UmlClass umlClass) {
        this.wrappedObject = wrappedObject;
        this.umlClass = umlClass;
    }
    
    public W getWrappedObject() {
        return wrappedObject;
    }

    @Override
    public UmlClass getUmlClass() {
        return umlClass;
    }

    @Override
    public Long getId() {
        return -1l;
    }

    @Override
    public OclValue<?> getProperty(String propertyName) throws IllegalArgumentException, NotInitializedProperty {
        throw new IllegalArgumentException(umlClass.getClass()+ " objects have no property");
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.wrappedObject);
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
        final UmlWrapperObject<W> other = (UmlWrapperObject<W>) obj;
        if (!Objects.equals(this.wrappedObject, other.wrappedObject)) {
            return false;
        }
        if (!Objects.equals(this.umlClass, other.umlClass)) {
            return false;
        }
        return true;
    }
}
