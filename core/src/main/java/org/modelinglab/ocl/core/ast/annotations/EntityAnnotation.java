/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.annotations;

import java.io.Serializable;

/**
 * This is an OCL element annotation that marks entities (UML classes stored in database).
 * 
 * @deprecated This class should be removed form here and moved to AG Core
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
@Deprecated
public class EntityAnnotation implements Serializable {
    private static final long serialVersionUID = 1L;

    private EntityAnnotation() {
    }

    public static EntityAnnotation getInstance() {
        return EntityAnnotationHolder.INSTANCE;
    }
    
    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == this.getClass();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    private static class EntityAnnotationHolder {

        private static final EntityAnnotation INSTANCE = new EntityAnnotation();
    }
}
