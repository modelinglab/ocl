/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.utils.environments.java;

import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.utils.m2m.ModelTransformation;
import org.modelinglab.utils.m2m.Translator;
import org.modelinglab.utils.m2m.exceptions.NotTranslatableException;
import org.modelinglab.utils.m2m.exceptions.TranslationException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ArrayTranslator implements Translator<Class, CollectionType, JavaToUmlTransformationContext> {

    private ArrayTranslator() {
    }

    @Override
    public void translate(
            ModelTransformation m2m, 
            Class input, 
            CollectionType output, 
            JavaToUmlTransformationContext context) throws TranslationException, NotTranslatableException {
        
        assert input.isArray();
        assert input.getComponentType() != null;
        
        Classifier componentClassifier = (Classifier) m2m.getTranslation(input.getComponentType(), context);
        output.setElementType(componentClassifier);
    }

    public static ArrayTranslator getInstance() {
        return ArrayTranslatorHolder.INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return ArrayTranslator.getInstance();
    }

    

    private static class ArrayTranslatorHolder {
        private static final ArrayTranslator INSTANCE = new ArrayTranslator();
    }
 }
