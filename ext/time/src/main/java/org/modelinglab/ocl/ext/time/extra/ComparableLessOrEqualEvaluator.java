/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time.extra;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlClass;

/**
 *
 */
public class ComparableLessOrEqualEvaluator<C extends Comparable<C>> extends ComparableAbstractEvaluator<C> {

    public ComparableLessOrEqualEvaluator(UmlClass classifier, Class<C> clazz) throws SecurityException, NoSuchMethodException {
        super(classifier, clazz);
    }

    @Override
    protected boolean comparableToBoolean(Integer comparableResult) {
        return comparableResult <= 0;
    }
    
    @Override
    public Operation getEvaluableOperation() {
        return new ComparableOperation(getUmlClass(), "<=");
    }

}