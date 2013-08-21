/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time;

import com.google.common.base.Predicate;
import java.lang.reflect.Method;

/**
 *
 */
public class ValidTypesMethodFilter implements Predicate<Method> {

    private ValidTypesMethodFilter() {
    }

    public static ValidTypesMethodFilter getInstance() {
        return ValidTypesMethodFilterHolder.INSTANCE;
    }
    
    @Override
    public boolean apply(Method input) {
        
        if (!DateUtils.isTranslatable(input.getDeclaringClass())) {
            return false;
        }
        if (!DateUtils.isTranslatable(input.getReturnType())) {
            return false;
        }
        for (final Class<?> class1 : input.getParameterTypes()) {
            if (!DateUtils.isTranslatable(class1)) {
                return false;
            }
        }
        return true;

    }

    /**
     * This method is called immediately after an object of this class is deserialized.
     * This method returns the singleton instance.
     */
    protected Object readResolve() {
        return getInstance();
    }

    private static class ValidTypesMethodFilterHolder {
        private static final ValidTypesMethodFilter INSTANCE = new ValidTypesMethodFilter();
    }
 }
