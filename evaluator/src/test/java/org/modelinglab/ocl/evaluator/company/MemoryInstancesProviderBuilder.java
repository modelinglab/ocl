/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.company;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.SetValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class MemoryInstancesProviderBuilder {
    
    private Map<UmlClass, Set<ObjectValue<?>>> instances = new HashMap<>();
    
    public boolean addInstance(@Nonnull UmlClass clazz, @Nonnull UmlObject<?> instance) {
        Preconditions.checkArgument(instance.getUmlClass().equals(clazz));
        
        Set<ObjectValue<?>> values = instances.get(clazz);
        if (values == null) {
            values = new HashSet<>();
            instances.put(clazz, values);
        }
        ObjectValue<?> value = new ObjectValue<>(instance);
        return values.add(value);
    }
    
    public MemoryInstanceProvider createInstancesProvider() {
        Map<UmlClass, SetValue<ObjectValue<?>>> map = new HashMap<>(instances.size());
        for (UmlClass umlClass : instances.keySet()) {
            SetValue<ObjectValue<?>> set = new SetValue<>(instances.get(umlClass), umlClass);
            map.put(umlClass, set);
        }
        return new MemoryInstanceProvider(map);
    }
    
}
