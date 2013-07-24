/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.company.MemoryInstanceProvider;
import org.modelinglab.ocl.evaluator.company.MemoryInstancesProviderBuilder;
import org.modelinglab.ocl.evaluator.company.MemoryUmlObject;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class DefaultInstancesProvider implements InstanceProvider {
    private static final long serialVersionUID = 1L;
    
    MemoryInstanceProvider memoryInstanceProvider;

    public DefaultInstancesProvider(StaticEnvironment env) {
        
        MemoryInstancesProviderBuilder constructor = new MemoryInstancesProviderBuilder();
        
        constructor.addInstance(MyNumber.INSTANCE, new MyNumber.MyNumberObject(1l));
        constructor.addInstance(MyNumber.INSTANCE, new MyNumber.MyNumberObject(2l));
        constructor.addInstance(MyNumber.INSTANCE, new MyNumber.MyNumberObject(3l));
        
        
        memoryInstanceProvider = constructor.createInstancesProvider();
    }

    @Override
    public void preExpression(OclExpression exp, UserEvaluationEnvironment env) {
        memoryInstanceProvider.preExpression(exp, env);
    }

    @Override
    public void postExpression(OclExpression exp, UserEvaluationEnvironment env) {
        memoryInstanceProvider.postExpression(exp, env);
    }

    @Override
    public SetValue<ObjectValue<?>> getAllInstances(UmlClass clazz) {
        return memoryInstanceProvider.getAllInstances(clazz);
    }

    @Override
    public void checkObject(ObjectValue val) throws UnexpectedObjectException {
        //TODO: implement it
    }
    
}
