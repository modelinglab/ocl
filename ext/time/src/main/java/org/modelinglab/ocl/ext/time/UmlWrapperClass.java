/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time;

import java.lang.reflect.Modifier;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlObject;

/**
 *
 */
public class UmlWrapperClass extends UmlClass {

    public UmlWrapperClass(Class<?> clazz, ClassBinderResolver resolver) {
        setName(clazz.getSimpleName());

        setAbstract(Modifier.isAbstract(clazz.getModifiers()));
        for (final Class<?> interface1 : clazz.getInterfaces()) {
            ClassBinder<?> binder = resolver.getBinder(interface1);
            if (binder != null) {
                addSuperClass(binder.getUmlClas());
            }
        }
        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            ClassBinder<?> binder = resolver.getBinder(clazz.getSuperclass());
            if (binder != null) {
                addSuperClass(binder.getUmlClas());
            }
        }
    }
}
