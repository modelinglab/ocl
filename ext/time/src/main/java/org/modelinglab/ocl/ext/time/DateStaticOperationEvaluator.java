/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time;

import java.lang.reflect.Method;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 */
class DateStaticOperationEvaluator extends DateOperationEvaluator {
 
    public DateStaticOperationEvaluator(Operation evaluableOperation, Method method) {
        super(evaluableOperation, method);
    }

    @Override
    public OclValue<?> visit(ClassifierValue val, SwitchArgument arg) {
        return evaluate(null, arg);
    }

}
