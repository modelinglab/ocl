/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.extra;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.Classifier;

/**
 *
 */
public class ComparableGreaterOrEqualEvaluator extends ComparableAbstractEvaluator {

    public ComparableGreaterOrEqualEvaluator(UmlClass classifier, Class<?> clazz) throws SecurityException, NoSuchMethodException {
        super(classifier, clazz);
    }

    @Override
    protected boolean comparableToBoolean(Integer comparableResult) {
        return comparableResult >= 0;
    }
    
    @Override
    public Operation getEvaluableOperation() {
        return new ComparableOperation(getUmlClass(), ">=");
    }

}
