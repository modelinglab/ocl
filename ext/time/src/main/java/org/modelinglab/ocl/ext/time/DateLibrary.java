/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time;

import com.google.common.base.Predicate;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.OperationsStore;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.time.extra.*;
import org.threeten.bp.Duration;

/**
 *
 */
public class DateLibrary {

    private Set<Classifier> dateTypes = ClassBinderResolverImpl.getInstance().getDateTpes();

    public Set<Classifier> createTypes() {
        return Collections.unmodifiableSet(dateTypes);
    }

    public OperationsStore createStore() {
        OperationsStore.OperationsStoreFactory factory = new OperationsStore.OperationsStoreFactory();

        Set<Method> methods = getMethods();
        for (final Method method : methods) {
            factory.addOperation(new DateOperation(method));
        }

        for (final Classifier classifier : dateTypes) {
            if (classifier instanceof UmlClass) {
                UmlClass umlClass = (UmlClass) classifier;
                ClassBinder<?> binder = ClassBinderResolverImpl.getInstance().getBinder(umlClass);
                if (binder != null) {
                    factory.addAllOperations(binder.getExtraOperations());
                }

                factory.addAllOperations(getDefaultOperations(binder));
            }
        }

        return factory.createStore();
    }

    public Set<OperationEvaluator> getEvaluators() {
        HashSet<OperationEvaluator> result = new HashSet<>();

        Set<Method> methods = getMethods();
        for (final Method method : methods) {
            if (Modifier.isStatic(method.getModifiers())) {
                result.add(new DateStaticOperationEvaluator(new DateOperation(method), method));
            } else {
                result.add(new DateInstanceOperationEvaluator(new DateOperation(method), method));
            }
        }

        for (final Classifier classifier : dateTypes) {
            if (classifier instanceof UmlClass) {
                UmlClass umlClass = (UmlClass) classifier;
                ClassBinder<?> binder = ClassBinderResolverImpl.getInstance().getBinder(umlClass);
                if (binder != null) {
                    result.addAll(binder.getExtraEvaluators());
                }
                result.addAll(getDefaultEvaluators(binder));
            }
        }

        return result;
    }

    private Set<Method> getMethods() {
        HashSet<Method> methods = new HashSet<>();
        for (final Classifier classifier : dateTypes) {
            if (classifier instanceof UmlClass) {
                UmlClass umlClass = (UmlClass) classifier;
                ClassBinder<?> binder = ClassBinderResolverImpl.getInstance().getBinder(umlClass);
                if (binder != null) {
                    Predicate<Method> filter = binder.getMethodFilter();

                    for (final Method method : binder.getJavaClass().getDeclaredMethods()) {
                        if (Modifier.isPublic(method.getModifiers())) {
                            if (filter.apply(method)) {
                                methods.add(method);
                            }
                        }
                    }
                }
            }
        }

        return methods;
    }

    private Set<Operation> getDefaultOperations(ClassBinder<?> binder) {
        Set<Operation> result = new HashSet<>();

        Class<?> clazz = binder.getJavaClass();

        try {
            Method m = clazz.getMethod("compareTo", clazz);
            if (m != null) {
                result.add(new ComparableLessEvaluator<>(binder.getUmlClas(), Duration.class).
                        getEvaluableOperation());
                result.add(new ComparableLessOrEqualEvaluator<>(binder.getUmlClas(), Duration.class).
                        getEvaluableOperation());
                result.add(new ComparableGreaterEvaluator<>(binder.getUmlClas(), Duration.class).
                        getEvaluableOperation());
                result.add(new ComparableGreaterOrEqualEvaluator<>(binder.getUmlClas(), Duration.class).
                        getEvaluableOperation());
            }
        } catch (NoSuchMethodException ex) {
            //nothing to do
        } catch (SecurityException ex) {
            throw new IllegalArgumentException(ex);
        }

        if (!Modifier.isAbstract(clazz.getModifiers())) {
            result.add(new EqualsOperation(binder.getUmlClas()));
        }

        return result;
    }

    private Set<OperationEvaluator> getDefaultEvaluators(ClassBinder<?> binder) {
        Set<OperationEvaluator> result = new HashSet<>();

        Class<?> clazz = binder.getJavaClass();

        try {
            Method m = clazz.getMethod("compareTo", clazz);
            if (m != null) {
                result.add(new ComparableLessEvaluator<>(binder.getUmlClas(), Duration.class));
                result.add(new ComparableLessOrEqualEvaluator<>(binder.getUmlClas(), Duration.class));
                result.add(new ComparableGreaterEvaluator<>(binder.getUmlClas(), Duration.class));
                result.add(new ComparableGreaterOrEqualEvaluator<>(binder.getUmlClas(), Duration.class));
            }
        } catch (NoSuchMethodException ex) {
            //nothing to do
        } catch (SecurityException ex) {
            throw new IllegalArgumentException(ex);
        }
        if (!Modifier.isAbstract(clazz.getModifiers())) {
            try {
                result.add(new EqualsEvaluator(binder.getUmlClas(), clazz));
            } catch (SecurityException | NoSuchMethodException ex) {
                throw new AssertionError(); //this should not happen
            }
        }

        return result;
    }
}
