/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.ReferenceUmlObject;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlObject;

/**
 *
 * @param <Id> class used to identify the object. For example, in relationship databases, that id should be
 * an integer
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface UmlObjectFactory<Id> {
    
    public UmlObject<Id> createUmlValue(UmlClass clazz, Id id) throws IllegalArgumentException, IllegalStateException;
    
    
    public static class DefaultUmlObjectFactory<Id> implements UmlObjectFactory<Id> {

        @Override
        public UmlObject<Id> createUmlValue(UmlClass clazz, Id id) throws IllegalArgumentException, IllegalStateException {
            return new ReferenceUmlObject<>(clazz, id);
        }

    }
}
