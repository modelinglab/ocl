/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.extra;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.time.UmlWrapperObject;

/**
 *
 */
public class EqualsEvaluator extends OperationEvaluator {

    private UmlClass classifier;
    private Method equals;
    
    public EqualsEvaluator(UmlClass classifier, Class<?> clazz) throws SecurityException, NoSuchMethodException {
        equals = clazz.getMethod("equals", clazz);
        this.classifier = classifier;
    }

    @Override
    public OclValue<?> visit(ObjectValue<?> val, SwitchArgument arg) {
        if (arg.arguments.get(0).getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        assert val.getValue() instanceof UmlWrapperObject;
        UmlWrapperObject valObject = (UmlWrapperObject) val.getValue();
        
        assert arg.arguments.get(0).getValue() instanceof UmlWrapperObject;
        UmlWrapperObject argObject = (UmlWrapperObject) arg.arguments.get(0).getValue();
        
        try {
            Object obj = equals.invoke(valObject.getWrappedObject(), argObject.getWrappedObject());
            
            if ((Boolean) obj) {
                return BooleanValue.TRUE;
            }
            return BooleanValue.FALSE;
            
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new IllegalArgumentException(ex);
        }
        
    }

    @Override
    public Operation getEvaluableOperation() {
        return new EqualsOperation(classifier);
    }


}
