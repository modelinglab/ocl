/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.utils.environments.java;

import com.google.common.base.Preconditions;
import org.modelinglab.utils.m2m.ClassTranslatorFactory;
import org.modelinglab.utils.m2m.Translator;
import org.modelinglab.utils.m2m.exceptions.NotTranslatableException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
final class JavaToUmlTranslatorFactory extends ClassTranslatorFactory {

    @Nonnull
    @Override
    public Translator getTranslator(Object input) throws NotTranslatableException {
        Preconditions.checkArgument(input instanceof Class);

        Class clazz = (Class) input;

        if (clazz.isArray()) { //special case: input is an array
            return ArrayTranslator.getInstance();
        }
        if (Collection.class.isAssignableFrom(clazz)) { //special case: input is a collection
            if (Set.class.isAssignableFrom(clazz) || List.class.isAssignableFrom(clazz)) {
                return CollectionTranslator.getInstance();
            } else {
                throw new UnsupportedOperationException(clazz.getName() + " collection is not supported.");
            }
        }
        if (Map.class.isAssignableFrom(clazz)) { //another special case
            //TODO: Throw an exception... or implement?
            throw new UnsupportedOperationException("Not supported yet.");
        }
        if (clazz.isEnum()) {
            return EnumTranslator.getInstance();
        }
        else { //input is not a "normal" class
            assert !clazz.isAnonymousClass() && !clazz.isSynthetic() && !clazz.isPrimitive() : "Invalid java class that " + JavaToUmlObjectFactory.class + " should have filtrate.";
            return ClassTranslator.getInstance();
        }

    }
}
