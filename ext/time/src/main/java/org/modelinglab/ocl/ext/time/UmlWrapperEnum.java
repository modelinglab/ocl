/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;

/**
 *
 */
public class UmlWrapperEnum extends UmlEnum {

    public UmlWrapperEnum(Class<?> clazz, ClassBinderResolver resolver) {
        try {
            setName(clazz.getSimpleName());

            setAbstract(Modifier.isAbstract(clazz.getModifiers()));
            for (final Class<?> interface1 : clazz.getInterfaces()) {
                ClassBinder<?> binder = resolver.getBinder(interface1);
                if (binder != null) {
                    addSuperClass(binder.getUmlClas());
                }
            }
            ClassBinder<?> binder = resolver.getBinder(clazz.getSuperclass());
            if (binder != null) {
                addSuperClass(binder.getUmlClas());
            }

            Method nameMethod = clazz.getMethod("name");
            for (final Object literal : clazz.getEnumConstants()) {
                String name = (String) nameMethod.invoke(literal);
                addLiteral(new UmlEnumLiteral(name));
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }
}
