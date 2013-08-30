/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time;

import com.google.common.base.Predicate;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.values.EnumValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 */
public class ClassBinder<Wrapped> {

    private transient UmlClass umlClass;
    private final Class<Wrapped> javaClass;
    private final Predicate<Method> methodFilter;
    private final ClassBinderResolver resolver;

    public ClassBinder(Class<Wrapped> javaClass, Predicate<Method> methodFilter, ClassBinderResolver resolver) {
        this.javaClass = javaClass;
        this.methodFilter = methodFilter;
        this.resolver = resolver;
    }
    
    /**
     * This method is called when the UmlClass (or UmlEnum!) is initialized.
     * 
     * It can be used to add annotations to the created class.
     * 
     * @param clazz 
     */
    protected void initializeCreatedClass(UmlClass clazz) {}

    public UmlClass getUmlClas() {
        if (umlClass == null) {
            if (javaClass.isEnum()) {
                umlClass = new UmlWrapperEnum(javaClass, resolver);
                initializeCreatedClass(umlClass);
            }
            else {
                umlClass = new UmlWrapperClass(javaClass, resolver);
                initializeCreatedClass(umlClass);
            }
        }
        return umlClass;
    }

    public Class<Wrapped> getJavaClass() {
        return javaClass;
    }

    public Predicate<Method> getMethodFilter() {
        return methodFilter;
    }

    public Set<Operation> getExtraOperations() {
        return Collections.emptySet();
    }

    public Set<OperationEvaluator> getExtraEvaluators() {
        return Collections.emptySet();
    }

    public OclValue<?> getOclValue(Object wrapped) {
        if (javaClass.isEnum()) {
            UmlEnum oclEnum = (UmlEnum) getUmlClas();
            Enum javaEnum = (Enum) wrapped;
            return new EnumValue(oclEnum.getLiteral(javaEnum.ordinal()));
        } else {
            return new ObjectValue<>(new UmlWrapperObject<>(wrapped, getUmlClas()));
        }
    }
    
    public <T extends Enum<T>> Object getJavaObject(OclValue<?> oclValue) {
        if (oclValue instanceof ObjectValue) {
            return ((UmlWrapperObject) oclValue.getValue()).getWrappedObject();
        }
        else {
            String literalName = ((EnumValue) oclValue).getValue().getName();
            Class<T> c = (Class<T>) getJavaClass();
            
            return Enum.valueOf(c, literalName);
        }
    }
}
