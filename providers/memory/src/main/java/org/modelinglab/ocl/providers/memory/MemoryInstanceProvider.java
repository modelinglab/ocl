/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.providers.memory;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.annotations.EntityAnnotation;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.UserEvaluationEnvironment;
import org.modelinglab.ocl.evaluator.InstanceProvider;
import org.modelinglab.ocl.evaluator.UnexpectedObjectException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class MemoryInstanceProvider implements InstanceProvider {
    private static final long serialVersionUID = 1L;

    private final Map<UmlClass, SetValue<ObjectValue<?>>> instances;
    
    MemoryInstanceProvider(Map<UmlClass, SetValue<ObjectValue<?>>> instances) {
        this.instances = instances;
    }

    @Override
    public void postExpression(OclExpression oe, UserEvaluationEnvironment ee) {
    }

    @Override
    public void preExpression(OclExpression oe, UserEvaluationEnvironment ee) {
    }

    @Override
    public SetValue<ObjectValue<?>> getAllInstances(UmlClass clazz) {
        if (instances.containsKey(clazz)) {
            Set<ObjectValue<?>> emptySet = Collections.emptySet();
            SetValue<ObjectValue<?>> set = new SetValue<>(emptySet, clazz);
            instances.put(clazz, set);
        }
        return instances.get(clazz);
    }

    @Override
    public void checkObject(ObjectValue val) throws UnexpectedObjectException {
        if (val.getType().getAnnotation(EntityAnnotation.class) != null) {
            SetValue<ObjectValue<?>> subInstances = instances.get(val.getType());
            if (subInstances == null) {
                throw new UnexpectedObjectException(val);
            }
            if (!subInstances.getValue().contains(val)) {
                throw new UnexpectedObjectException(val);
            }
        }
    }
}
