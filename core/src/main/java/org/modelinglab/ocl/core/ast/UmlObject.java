/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import java.io.Serializable;
import org.modelinglab.ocl.core.exceptions.OclException;
import org.modelinglab.ocl.core.exceptions.OclRuntimeException;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @param <Id> class used to identify that object. For example, in relationship databases, that id should be
 *             an integer
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface UmlObject<Id> extends Serializable, Cloneable {

    public UmlClass getUmlClass();

    public Id getId();

    public OclValue<?> getProperty(String propertyName) throws IllegalArgumentException, NotInitializedProperty;
    
    public static class NotInitializedProperty extends OclRuntimeException {

        private static final long serialVersionUID = 1L;
        private final UmlObject<?> object;
        private final String propertyName;
        private final UmlClass clazz;

        public NotInitializedProperty(String propertyName, UmlClass clazz) {
            super("All UmlObjects of class " + clazz.getName() + " must give a value to "
                    + propertyName + " feature.");
            this.propertyName = propertyName;
            this.clazz = clazz;
            object = null;
        }

        public NotInitializedProperty(String propertyName, UmlClass clazz, String message) {
            super(message);
            this.propertyName = propertyName;
            this.clazz = clazz;
            object = null;
        }

        public NotInitializedProperty(String propertyName, UmlClass clazz, String message, Throwable cause) {
            super(message, cause);
            this.propertyName = propertyName;
            this.clazz = clazz;
            object = null;
        }

        public NotInitializedProperty(String propertyName, UmlClass clazz, Throwable cause) {
            super("All UmlObjects of class " + clazz.getName() + " must give a value to "
                    + propertyName + " feature.", cause);
            this.propertyName = propertyName;
            this.clazz = clazz;
            object = null;
        }

        public NotInitializedProperty(UmlObject<?> object, String propertyName, UmlClass clazz) {
            super("All UmlObjects of class " + clazz.getName() + " must give a value to "
                    + propertyName + " feature.");
            this.object = object;
            this.propertyName = propertyName;
            this.clazz = clazz;
        }

        public NotInitializedProperty(UmlObject<?> object, String propertyName, UmlClass clazz, String message) {
            super(message);
            this.object = object;
            this.propertyName = propertyName;
            this.clazz = clazz;
        }

        public NotInitializedProperty(UmlObject<?> object, String propertyName, UmlClass clazz, String message, Throwable cause) {
            super(message, cause);
            this.object = object;
            this.propertyName = propertyName;
            this.clazz = clazz;
        }

        public NotInitializedProperty(UmlObject<?> object, String propertyName, UmlClass clazz, Throwable cause) {
            super("All UmlObjects of class " + clazz.getName() + " must give a value to "
                    + propertyName + " feature.", cause);
            this.object = object;
            this.propertyName = propertyName;
            this.clazz = clazz;
        }

        public UmlObject<?> getObject() {
            return object;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public UmlClass getClazz() {
            return clazz;
        }
    }
}
