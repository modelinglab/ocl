/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;

/**
 *
 */
public class DateOperation extends Operation {

    public DateOperation(Method method) {
        super(null);
        
        setName(method.getName());
        Classifier type = DateUtils.translateToClassifier(method.getDeclaringClass());
        if (Modifier.isStatic(method.getModifiers())) {
            setSource(type.getClassifierType());
        }
        else {
            setSource(type);
        }
        setType(DateUtils.translateToClassifier(method.getReturnType()));
        
        if (method.getParameterAnnotations().length != 0) {
            Map<Classifier, Integer> argRepetitionMap = new HashMap<>(5);
            
            for (final Class<?> arg : method.getParameterTypes()) {
                Parameter p = new Parameter();
                Classifier argType = DateUtils.translateToClassifier(arg);
                p.setName(getArgName(argType, argRepetitionMap));
                p.setType(argType);
                addOwnedParameter(p);
            }
        }
    }

    public static boolean isMappeable(Method method) {
        //TODO: Improve this method
        try {
            DateOperation op = new DateOperation(method);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType,
                                       List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }
    
    protected static Method getMethod(Class<?> clazz, String methodName, Class<?>... argTypes) {
        try {
            return clazz.getMethod(methodName, argTypes);
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private String getArgName(Classifier argType, Map<Classifier, Integer> argRepetitionMap) {
        Integer i = argRepetitionMap.get(argType);
        if (i == null) {
            i = 1;
        }
        i++;
        argRepetitionMap.put(argType, i);
        
        return argType.getName() + i;
    }
}
