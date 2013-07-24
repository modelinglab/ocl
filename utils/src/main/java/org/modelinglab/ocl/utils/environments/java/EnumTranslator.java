/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.utils.environments.java;

import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.utils.m2m.ModelTransformation;
import org.modelinglab.utils.m2m.TransformationContext;
import org.modelinglab.utils.m2m.Translator;
import org.modelinglab.utils.m2m.exceptions.NotTranslatableException;
import org.modelinglab.utils.m2m.exceptions.TranslationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
class EnumTranslator implements Translator<Class, UmlEnum, JavaToUmlTransformationContext> {

    private EnumTranslator() {
    }

    @Override
    public void translate(
            ModelTransformation m2m, 
            Class input, 
            UmlEnum output, 
            JavaToUmlTransformationContext context) throws TranslationException, NotTranslatableException {
        assert input.isEnum();
        
        ClassTranslator.getInstance().translate(m2m, input, output, context);
        
        Method getNameMethod;
        try {
             getNameMethod = input.getMethod("getName", new Class[]{});
        } catch (Exception ex) {
            throw new NotTranslatableException("As enumeration, it was expected that "+input
                    +" implements getName method", ex, input);
        }
        for (Object constant : input.getEnumConstants()) {
            String literalName;
            try {
                literalName = (String) getNameMethod.invoke(constant, new Object[]{});
            } catch (Exception ex) {
                throw new NotTranslatableException("It was imposible to get the name of that enum literal.", ex, constant);
            }
            output.addLiteral(new UmlEnumLiteral(literalName));
        }
        
    }

    public static EnumTranslator getInstance() {
        return EnumTranslatorHolder.INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return EnumTranslator.getInstance();
    }

    

    private static class EnumTranslatorHolder {
        private static final EnumTranslator INSTANCE = new EnumTranslator();
    }
 }
