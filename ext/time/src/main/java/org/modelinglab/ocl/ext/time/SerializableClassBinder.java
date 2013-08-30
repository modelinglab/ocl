/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time;

import com.google.common.base.Predicate;
import java.lang.reflect.Method;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.utils.sql.SQLSerializable;

/**
 *
 */
public class SerializableClassBinder<Wrapped> extends ClassBinder<Wrapped> {

    private final SQLSerializable serializable;

    public SerializableClassBinder(SQLSerializable serializable, Class<Wrapped> javaClass,
                                   Predicate<Method> methodFilter, ClassBinderResolver resolver) {
        super(javaClass, methodFilter, resolver);
        this.serializable = serializable;
    }
     
    @Override
    protected void initializeCreatedClass(UmlClass clazz) {
        clazz.setAnnotation(SQLSerializable.class, serializable);
    }
 
    
}
