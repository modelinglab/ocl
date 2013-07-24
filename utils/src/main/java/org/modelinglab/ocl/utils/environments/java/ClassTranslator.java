/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.utils.environments.java;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.types.MultiplicityElement;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.utils.m2m.ModelTransformation;
import org.modelinglab.utils.m2m.Translator;
import org.modelinglab.utils.m2m.exceptions.NotTranslatableException;
import org.modelinglab.utils.m2m.exceptions.TranslationException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
class ClassTranslator implements Translator<Class, UmlClass, JavaToUmlTransformationContext> {

    @Override
    public void translate(
            ModelTransformation m2m, 
            Class input, 
            UmlClass output, 
            JavaToUmlTransformationContext context) throws TranslationException, NotTranslatableException {
        
        Preconditions.checkArgument(input.getName() != null, 
                "Only java classes with name can be translated to uml classes.");
        output.setName(input.getSimpleName());
        
        if (input.getSuperclass() != null && input.getSuperclass() != Object.class) {
            output.addSuperClass((UmlClass) m2m.getTranslation(input.getSuperclass(), context));
        }
        for (Class interfaze : input.getInterfaces()) {
            output.addSuperClass((UmlClass) m2m.getTranslation(interfaze, context));
        }
        
        for (Field field : input.getDeclaredFields()) {
            Class fieldClass = field.getType();
            if (Collection.class.isAssignableFrom(fieldClass)) {
                Type fieldType = field.getGenericType();
                assert fieldType instanceof ParameterizedType : "It was expected that type of collection types was ParametrizedType";
                
                final ParameterizedType parameterizedFieldType = (ParameterizedType) fieldType;
                assert parameterizedFieldType.getActualTypeArguments().length == 1;
                
                Class collectionElementClass = (Class) parameterizedFieldType.getActualTypeArguments()[0];
                if (collectionElementClass.getTypeParameters().length > 0) {
                    throw new NotTranslatableException("Collection fields whose types are not "
                            + "generic are the unique generic fields allowed be to map.", field);
                }
                
                int upperBound = MultiplicityElement.UNBOUND;
                boolean isOrdered = List.class.isAssignableFrom(fieldClass);
                
                
                fieldClass = collectionElementClass;
                
            }
            else {
                if (fieldClass.getTypeParameters().length > 0) {
                    throw new NotTranslatableException("Collection fields whose types are not "
                                + "generic are the unique generic fields allowed be to map.", field);
                }
            }
            
            Attribute att = new Attribute();
            att.setName(field.getName());
            att.setUpperBound(1);
            att.setLowerBound(0);
            att.setUnique(false);
            att.setOrdered(false);
            att.setReferredType((Classifier) m2m.getTranslation(fieldClass, context));
            
            output.addOwnedAttribute(att);
        }
        
    }
    
    private ClassTranslator() {
    }

    public static ClassTranslator getInstance() {
        return ClassTranslator.ClassTranslatorHolder.INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return ClassTranslator.getInstance();
    }

    

    private static class ClassTranslatorHolder {
        private static final ClassTranslator INSTANCE = new ClassTranslator();
    }
    
}
