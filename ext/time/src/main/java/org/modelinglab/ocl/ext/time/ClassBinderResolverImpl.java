/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time;

import com.google.common.collect.Sets;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.ext.time.serializers.DateSQLSerializerProvider;
import org.modelinglab.ocl.utils.sql.SQLSerializable;

/**
 *
 */
public class ClassBinderResolverImpl implements ClassBinderResolver {

    private final Map<Class<?>, ClassBinder<?>> javaClassToClassBinder;
    private final Map<UmlClass, ClassBinder<?>> umlClassToClassBinder;
    private final Set<Class<?>> ignoredClasses;

    private ClassBinderResolverImpl() {
        ignoredClasses = Sets.<Class<?>>newHashSet(Serializable.class);

        List<Class<?>> mappedClasses = MappedClassProvider.getMappedClasses();
        javaClassToClassBinder = new HashMap<>(mappedClasses.size());
        umlClassToClassBinder = new HashMap<>(mappedClasses.size());

        Map<Class<?>, SQLSerializable> serializableMap = DateSQLSerializerProvider.getSerializables();

        for (final Class<?> clazz : mappedClasses) {
            ClassBinder<?> binder;
            if (serializableMap.containsKey(clazz)) {
                binder = new SerializableClassBinder<>(
                        serializableMap.get(clazz), clazz, 
                        ValidTypesMethodFilter.
                        getInstance(), 
                        this);
            }
            else {
                binder = new ClassBinder<>(clazz, ValidTypesMethodFilter.getInstance(), this);
            }
            javaClassToClassBinder.put(clazz, binder);
        }
        for (final ClassBinder<?> classBinder : javaClassToClassBinder.values()) {
            umlClassToClassBinder.put(classBinder.getUmlClas(), classBinder);
        }
    }

    public static ClassBinderResolverImpl getInstance() {
        return DateBinderLoaderHolder.INSTANCE;
    }

    @Override
    public <C> ClassBinder<C> getBinder(Class<C> clazz) throws IllegalArgumentException {
        ClassBinder<C> result = (ClassBinder<C>) javaClassToClassBinder.get(clazz);
        if (result == null && !isIgnored(clazz)) {
            throw new IllegalArgumentException(clazz + " is not a bound type");
        }
        return result;
    }

    private boolean isIgnored(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        if (packageName.equals("java.lang") || packageName.equals("java.util")) {
            return true;
        }
        if (packageName.equals("org.threeten.bp.jdk8")) {
            return true;
        }
        if (!Modifier.isPublic(clazz.getModifiers())) {
            return true;
        }
        return ignoredClasses.contains(clazz);
    }

    @Override
    public ClassBinder<?> getBinder(UmlClass classifier) throws IllegalArgumentException {
        ClassBinder<?> result = (ClassBinder<?>) umlClassToClassBinder.get(classifier);
        if (result == null) {
            throw new IllegalArgumentException(classifier + " is not a bound type");
        }
        return result;
    }

    public Set<Classifier> getDateTpes() {
        Collection<ClassBinder<?>> binders = javaClassToClassBinder.values();
        HashSet result = new HashSet(binders.size());

        for (final ClassBinder<?> classBinder : binders) {
            result.add(classBinder.getUmlClas());
        }
        return result;
    }

    public Map<Class<?>, ClassBinder<?>> getJavaClassToClassBinder() {
        return javaClassToClassBinder;
    }

    public Map<UmlClass, ClassBinder<?>> getUmlClassToClassBinder() {
        return umlClassToClassBinder;
    }

    /**
     * This method is called immediately after an object of this class is deserialized. This method returns
     * the singleton instance.
     */
    protected Object readResolve() {
        return getInstance();
    }

    private static class DateBinderLoaderHolder {

        private static final ClassBinderResolverImpl INSTANCE = new ClassBinderResolverImpl();
    }
}
