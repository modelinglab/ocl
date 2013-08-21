/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time;

import java.util.*;
import javax.annotation.Nullable;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitorAdaptor;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.EnumValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.core.values.TupleValue;
import org.modelinglab.ocl.core.values.VoidValue;

/**
 *
 */
public class DateUtils {

    private final static Map<Class<?>, Classifier> primitiveTypes;
    private final static Map<Class<?>, ClassBinder<?>> javaClassToClassBinder = ClassBinderResolverImpl.
            getInstance().getJavaClassToClassBinder();
    private final static Map<UmlClass, ClassBinder<?>> umlClassToClassBinder = ClassBinderResolverImpl.
            getInstance().getUmlClassToClassBinder();
    private final static JavaObjectToOclObject jotoo = new JavaObjectToOclObject();

    static {
        primitiveTypes = new HashMap<>();
        primitiveTypes.put(int.class, PrimitiveType.INTEGER);
        primitiveTypes.put(Integer.class, PrimitiveType.INTEGER);
        primitiveTypes.put(long.class, PrimitiveType.INTEGER);
        primitiveTypes.put(Long.class, PrimitiveType.INTEGER);

        primitiveTypes.put(float.class, PrimitiveType.REAL);
        primitiveTypes.put(double.class, PrimitiveType.REAL);
        primitiveTypes.put(Float.class, PrimitiveType.REAL);
        primitiveTypes.put(Double.class, PrimitiveType.REAL);

        primitiveTypes.put(boolean.class, PrimitiveType.BOOLEAN);
        primitiveTypes.put(Boolean.class, PrimitiveType.BOOLEAN);

        primitiveTypes.put(String.class, PrimitiveType.STRING);
    }

    public static Classifier translateToClassifier(Class<?> clazz) {
        ClassBinder<?> binder = javaClassToClassBinder.get(clazz);
        if (binder == null) {
            Classifier primitiveType = primitiveTypes.get(clazz);
            if (primitiveType == null) {
                throw new IllegalArgumentException("It is not defined how to translate " + clazz);
            }
            return primitiveType;
        }
        return binder.getUmlClas();
    }

    public static boolean isTranslatable(Class<?> clazz) {
        return javaClassToClassBinder.containsKey(clazz) || primitiveTypes.containsKey(clazz);
    }

    public static Object translateToJavaObject(OclValue<?> oclValue) {
        if (oclValue.getType().isCollection()) {
            throw new IllegalArgumentException("Collections are not supported");
        }
        if (oclValue instanceof TupleValue) {
            throw new IllegalArgumentException("Tuples are not supported");
        }
        if (oclValue.getType() instanceof UmlClass) {
            UmlClass umlClass = (UmlClass) oclValue.getType();
            ClassBinder binder = umlClassToClassBinder.get(umlClass);
            if (binder == null) {
                throw new IllegalArgumentException(oclValue.getType() + " is not a bound type");
            }
            return binder.getJavaObject(oclValue);
        }
        return oclValue.getValue();
    }

    public static OclValue<?> translateToOclObject(@Nullable Object javaObject, Classifier expectedType) {
        if (javaObject == null) {
            return VoidValue.instantiate();
        }
        return expectedType.accept(jotoo, javaObject);
    }

    private static class JavaObjectToOclObject extends ClassifierVisitorAdaptor<OclValue<?>, Object> {

        @Override
        public OclValue<?> visit(Classifier classifier, Object argument) {
            throw new IllegalArgumentException("I don't know who to translate java objects to " + classifier);
        }

        @Override
        public OclValue<?> visit(PrimitiveType primitiveType, Object argument) {
            switch (primitiveType.getPrimitiveKind()) {
                case BOOLEAN: {
                    assert argument instanceof Boolean;
                    Boolean b = (Boolean) argument;
                    return BooleanValue.createValue(b);
                }
                case INTEGER: {
                    Number n = (Number) argument;
                    return new IntegerValue(n.longValue());
                }
                case REAL: {
                    Number n = (Number) argument;
                    return new RealValue(n.doubleValue());
                }
                case STRING: {
                    String str = (String) argument;
                    return new StringValue(str);
                }
                default:
                    return super.visit(primitiveType, argument);
            }
        }

        @Override
        public OclValue<?> visit(UmlClass umlClass, Object argument) {

            ClassBinder<?> binder = umlClassToClassBinder.get(umlClass);

            if (binder == null) {
                throw new AssertionError("It is not defined how to translate " + argument.getClass() + " objects "
                                         + "into OCL objects");
            }
            return binder.getOclValue(argument);
        }

        @Override
        public OclValue<?> visit(UmlEnum umlEnum, Object argument) {
            UmlEnumLiteral literal;
            Enum<?> castedEnum = (Enum<?>) argument;

            literal = umlEnum.getLiteral(castedEnum.ordinal());

            return new EnumValue(literal);
        }

        @Override
        public OclValue<?> visit(ClassifierType classifierType, Object argument) {
            return new ClassifierValue(classifierType.getReferredClassifier());
        }
    }
}
