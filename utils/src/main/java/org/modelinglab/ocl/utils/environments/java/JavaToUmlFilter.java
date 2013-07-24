/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.utils.environments.java;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface JavaToUmlFilter {
    
    /**
     * @param javaClass the input class
     * @return true if and only if the java class should be translated to UmlClass
     */
    public boolean accept(Class javaClass);
    
    
    public static class AcceptAll implements JavaToUmlFilter {

        @Override
        public boolean accept(Class javaClass) {
            return true;
        }
        
    }
    
}
