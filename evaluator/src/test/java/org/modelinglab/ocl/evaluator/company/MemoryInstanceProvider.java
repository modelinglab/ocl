/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.company;

import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.evaluator.InstanceProvider;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.UserEvaluationEnvironment;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.evaluator.UnexpectedObjectException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class MemoryInstanceProvider implements InstanceProvider {
    private static final long serialVersionUID = 1L;

    private final Map<UmlClass, SetValue<ObjectValue<?>>> instances;
    
    MemoryInstanceProvider(Map<UmlClass, SetValue<ObjectValue<?>>> instances) {
        this.instances = instances;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void postExpression(@Nonnull OclExpression exp, @Nonnull UserEvaluationEnvironment env) {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void preExpression(@Nonnull OclExpression exp, @Nonnull UserEvaluationEnvironment env) {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public SetValue<ObjectValue<?>> getAllInstances(@Nonnull UmlClass clazz) {
        if (!instances.containsKey(clazz)) {
            Set<ObjectValue<?>> emptySet = Collections.emptySet();
            SetValue<ObjectValue<?>> set = new SetValue<>(emptySet, clazz);
            instances.put(clazz, set);
        }
        return instances.get(clazz);
    }

    @Override
    public void checkObject(ObjectValue val) throws UnexpectedObjectException {
        //TODO: Implement it
    }
}
