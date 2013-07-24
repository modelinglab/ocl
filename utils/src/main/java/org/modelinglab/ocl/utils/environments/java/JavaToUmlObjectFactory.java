/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.utils.environments.java;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.utils.OrderedSet;
import org.modelinglab.utils.m2m.ObjectFactory;
import org.modelinglab.utils.m2m.exceptions.NotTranslatableException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
class JavaToUmlObjectFactory implements ObjectFactory {

    private final JavaToUmlFilter filter;

    public JavaToUmlObjectFactory(JavaToUmlFilter filter) {
        this.filter = filter;
    }
    
    @Override
    public Object createObject(Object input) {
        Preconditions.checkArgument(input instanceof Class);
        
        Class clazz = (Class) input;
        
        if (!filter.accept(clazz)) {
            return null;
        }
        
        if (clazz.isAnonymousClass()) {
            throw new NotTranslatableException("All UmlClasses has a name, so anonymous "
                    + "classes are not translateables.", clazz);
        }
        if (clazz.isSynthetic()) {
            //TODO: Throw an exception... or implement?
            throw new UnsupportedOperationException("Not supported yet.");
        }
        if (clazz.isPrimitive()) {
            throw new AssertionError("Primitive classes should be already translated.");
        }
        if (clazz.isArray()) { //special case: input is an array
            return new SequenceType();
        }
        if (Collection.class.isAssignableFrom(clazz)) { //special case: input is a collection
            if (OrderedSet.class.isAssignableFrom(clazz)) {
                return new OrderedSetType();
            }
            if (Set.class.isAssignableFrom(clazz)) {
                return new SetType();
            }
            if (List.class.isAssignableFrom(clazz)) {
                return new SequenceType();
            }
            else {
                throw new UnsupportedOperationException(clazz.getName() + " collection is not supported.");
            }
        }
        if (Map.class.isAssignableFrom(clazz)) { //another special case
            //TODO: Throw an exception... or implement?
            throw new UnsupportedOperationException("Not supported yet.");
        } 
        else { //input is not a "normal" class
            
            UmlClass result;
            
            if (clazz.isEnum()) {
                result = new UmlEnum();
            }
            else {
                result = new UmlClass();
            }
            return result;
        }
    }
    
}
