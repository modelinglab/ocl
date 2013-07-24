/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.utils;

import java.util.List;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class ListSubList {

    private ListSubList() {
    }

    public static OclValue<?> subOrderedSet(List<? extends OclValue<?>> list, OperationEvaluator.SwitchArgument arg, Classifier elementType) {
        List<? extends OclValue<?>> view = createView(list, arg);
        if (view == null) {
            return InvalidValue.instantiate();
        }
        return new OrderedSetValue<>(view, elementType, true);
    }

    public static OclValue<?> subSequence(List<? extends OclValue<?>> list, OperationEvaluator.SwitchArgument arg, Classifier elementType) {
        List<? extends OclValue<?>> view = createView(list, arg);
        if (view == null) {
            return InvalidValue.instantiate();
        }
        return new SequenceValue<>(view, elementType, true);
    }

    /**
     *
     * @param list
     * @param arg
     *
     * @return a sublist view if all works properly or null if an InvalidValue shoud be return by the
     *         operation
     */
    private static List<? extends OclValue<?>> createView(List<? extends OclValue<?>> list, OperationEvaluator.SwitchArgument arg) {
        /*
         * Checks in the first argument
         */
        OclValue<?> arg1 = arg.arguments.get(0);
        if (arg1 instanceof VoidValue || arg1 instanceof InvalidValue) {
            return null;
        }
        IntegerValue lowerVal = (IntegerValue) arg1;
        if (lowerVal.getValue() < 1) {
            return null;
        }
        if (lowerVal.getValue() > Integer.MAX_VALUE) { //it should never happend
            throw new OclEvaluationException(
                    arg.exp,
                    lowerVal.getValue() + " is to big to be a a valid argument in "
                    + arg.exp.getReferredOperation() + " operation.");
        }
        //finally, we have the lower value
        int lower = lowerVal.getValue().intValue() - 1; //OCL indexes starts in 1!

        /*
         * Checks in the last argument
         */
        OclValue<?> arg2 = arg.arguments.get(1);
        if (arg2 instanceof VoidValue || arg2 instanceof InvalidValue) {
            return null;
        }
        IntegerValue upperVal = (IntegerValue) arg2;
        if (upperVal.getValue() < lower) {
            return null;
        }
        if (upperVal.getValue() > list.size()) {
            return null;
        }
        if (upperVal.getValue() > Integer.MAX_VALUE) { //it should never happend
            throw new OclEvaluationException(
                    arg.exp,
                    upperVal.getValue() + " is to big to be a a valid argument in "
                    + arg.exp.getReferredOperation() + " operation.");
        }
        /*
         * OCL indexes starts in 1, but suborderset and subsequence are inclusive in upper and java sublist is
         * exclusive!
         */
        int upper = upperVal.getValue().intValue();
        if (upper > list.size()) {
            return null;
        }

        return list.subList(lower, upper);
    }
}
