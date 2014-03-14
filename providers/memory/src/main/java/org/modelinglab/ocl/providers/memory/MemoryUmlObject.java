/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.providers.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.modelinglab.ocl.core.ast.AssociationEnd;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.exceptions.OclException;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class MemoryUmlObject implements UmlObject<Long> {
    private static final long serialVersionUID = 1L;
    private static final  AtomicLong idProvider = new AtomicLong();
    
    private final UmlClass clazz;
    private final Map<String, OclValue> features;
    private final Long id;

    public MemoryUmlObject(UmlClass clazz, Map<String, OclValue> features) throws OclException {
        this.clazz = clazz;
        this.features = new HashMap<>(features);
        for (AssociationEnd associationEnd : clazz.getAllAssociationEnds()) {
            if (!features.containsKey(associationEnd.getName())) {
                throw new NotInitializedProperty(associationEnd.getName(), clazz);
            }
        }
        for (Attribute attribute : clazz.getAllAttributes()) {
            if (!features.containsKey(attribute.getName())) {
                throw new NotInitializedProperty(attribute.getName(), clazz);
            }
        }
        id = idProvider.getAndIncrement();
    }

    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public OclValue<?> getProperty(String propertyName) throws IllegalArgumentException, NotInitializedProperty {
        if (!features.containsKey(propertyName)) {
            /*
             * constructor ensures that 
             * MemoryUmlObject.allInstances()->
             *      foreach(o | not o.features.containsKey(k) 
             *          implies not o.clazz.getAllAttributes()->contains(a | a.getName = featureName))
             * 
             * and exactly the same for associations
             */
            throw new IllegalArgumentException("UmlClass "+clazz.getName()+" does not contain any "
                    + "feature with "+propertyName+" name!");
        }
        return features.get(propertyName);
    }

    public void setProperty(String propertyName, OclValue<?> propertyValue) {
        if (!features.containsKey(propertyName)) {
            throw new IllegalArgumentException("UmlClass "+clazz.getName()+" does not contain any "
                    + "feature with "+propertyName+" name!");
        }
        features.put(propertyName, propertyValue);
    }
    
    @Override
    public UmlClass getUmlClass() {
        return clazz;
    }
}
