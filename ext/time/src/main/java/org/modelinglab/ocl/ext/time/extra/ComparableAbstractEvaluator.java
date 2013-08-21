/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.extra;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator.SwitchArgument;
import org.modelinglab.ocl.ext.time.UmlWrapperObject;
import org.modelinglab.ocl.ext.time.DateUtils;

/**
 *
 */
public abstract class ComparableAbstractEvaluator<C extends Comparable<C>> extends OperationEvaluator {

    private Method compareTo;
    private UmlClass classifier;
    
    public ComparableAbstractEvaluator(UmlClass classifier, Class<C> clazz) throws SecurityException, NoSuchMethodException {
        compareTo = clazz.getMethod("compareTo", clazz);
        this.classifier = classifier;
    }
    
    protected abstract boolean comparableToBoolean(Integer comparableResult);

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
            Object obj = compareTo.invoke(valObject.getWrappedObject(), argObject.getWrappedObject());
            
            if (comparableToBoolean((Integer) obj)) {
                return BooleanValue.TRUE;
            }
            return BooleanValue.FALSE;
            
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new IllegalArgumentException(ex);
        }
        
    }
    
    protected UmlClass getUmlClass() {
        return classifier;
    }

}
