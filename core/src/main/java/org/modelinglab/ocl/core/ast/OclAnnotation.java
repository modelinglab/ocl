/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import java.io.Serializable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class OclAnnotation implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Object element;

    public OclAnnotation(Object element) {
        this.element = element;
    }

    public Object getElement() {
        return element;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OclAnnotation other = (OclAnnotation) obj;
        if (this.element != other.element && (this.element == null || !this.element.equals(other.element))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.element != null ? this.element.hashCode() : 0);
        return hash;
    }
}
