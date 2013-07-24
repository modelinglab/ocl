/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.utils.environments.java;

import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.utils.m2m.ModelTransformation;
import org.modelinglab.utils.m2m.Translator;
import org.modelinglab.utils.m2m.exceptions.NotTranslatableException;
import org.modelinglab.utils.m2m.exceptions.TranslationException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CollectionTranslator implements Translator<Class, CollectionType, JavaToUmlTransformationContext> {

    private CollectionTranslator() {
    }

    @Override
    public void translate(
            ModelTransformation m2m, 
            Class input, 
            CollectionType output, 
            JavaToUmlTransformationContext context) throws TranslationException, NotTranslatableException {
        
    }

    public static CollectionTranslator getInstance() {
        return CollectionTranslatorHolder.INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return CollectionTranslator.getInstance();
    }

    

    private static class CollectionTranslatorHolder {
        private static final CollectionTranslator INSTANCE = new CollectionTranslator();
    }
 }
