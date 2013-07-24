/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.company;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.modelinglab.ocl.core.ast.AssociationEnd;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class MemoryUmlObject implements UmlObject<Integer> {

    private static final long serialVersionUID = 1L;
    private final UmlClass clazz;
    private final Map<String, OclValue<?>> features;
    private final Integer id;
    private static AtomicInteger idFactory = new AtomicInteger(0);

    public MemoryUmlObject(UmlClass clazz, Map<String, OclValue<?>> features) throws NotInitializedProperty {
        this.clazz = clazz;
        this.features = new HashMap<>(features);
        for (AssociationEnd associationEnd : clazz.getAllAssociationEnds()) {
            if (!features.containsKey(associationEnd.getName())) {
                throw new NotInitializedProperty("All UmlObjects of class " + clazz.getName() + " "
                        + "must give a value to "+ associationEnd.getName() + " feature.", clazz, associationEnd.getName());
            }
        }
        for (Attribute attribute : clazz.getAllAttributes()) {
            if (!features.containsKey(attribute.getName())) {
                throw new NotInitializedProperty("All UmlObjects of class " + clazz.getName() + " "
                        + "must give a value to "+ attribute.getName() + " feature.", clazz, attribute.getName());
            }
        }
        id = idFactory.getAndIncrement();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public OclValue<?> getProperty(String propertyName) throws IllegalArgumentException {
        if (!features.containsKey(propertyName)) {
            /*
             * constructor ensures that MemoryUmlObject.allInstances()-> foreach(o | not
             * o.features.containsKey(k) implies not o.clazz.getAllAttributes()->contains(a |
             * a.getName = featureName))
             *
             * and exactly the same for associations
             */
            throw new IllegalArgumentException("UmlClass " + clazz.getName() + " does not contain any "
                    + "feature with " + propertyName + " name!");
        }
        return features.get(propertyName);
    }
    
    public void setProperty(String propertyName, OclValue<?> value) {
        assert features.containsKey(propertyName);
        features.put(propertyName, value);
    }

    @Override
    public UmlClass getUmlClass() {
        return clazz;
    }

    @Override
    public UmlObject<Integer> clone() {
        return this;
    }
}
