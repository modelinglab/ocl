/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time;

import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.Classifier;

/**
 *
 */
public interface ClassBinderResolver {
    
    /**
     * 
     * @param <C>
     * @param clazz
     * @return the binder or null if the class can be ignored
     * @throws IllegalArgumentException if the given class is not binder and cannot be ignored
     */
    public <C> ClassBinder<C> getBinder(Class<C> clazz) throws IllegalArgumentException;
    
    public ClassBinder<?> getBinder(UmlClass classifier) throws IllegalArgumentException;
}
