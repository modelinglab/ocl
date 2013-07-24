/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.utils.environments.java;

import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.utils.m2m.TransformationContext;
import java.util.Map;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
class JavaToUmlTransformationContext implements TransformationContext {

    private Map<Class, Classifier> basicMap;

    public Map<Class, Classifier> getBasicMap() {
        return basicMap;
    }

    public void setBasicMap(Map<Class, Classifier> basicMap) {
        this.basicMap = basicMap;
    }
    
}
