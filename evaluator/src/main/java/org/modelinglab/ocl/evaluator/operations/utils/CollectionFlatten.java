/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;

/**
 * This class provides a general implementation of flatten lists of OclValues.
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionFlatten {

    private CollectionFlatten() {
    }

    public static SequenceValue<OclValue<?>> flatten(Collection<? extends OclValue<?>> original, Classifier elementType) {
        int size = getExpectedSize(original);
        ArrayList<OclValue<?>> resultList = new ArrayList<>(size);
        for (final OclValue<?> analyzedElement : original) {
            flattenRecursive(resultList, analyzedElement);
        }
        assert resultList.size() == size;
        return new SequenceValue<>(resultList, elementType, true);
    }
    
    static void flattenRecursive(List<OclValue<?>> resultList, OclValue<?> analyzedElement) {
        if (!analyzedElement.getType().isCollection()) {
            resultList.add(analyzedElement);
            return ;
        }
        @SuppressWarnings("unchecked")
        Collection<OclValue<?>> collectionValue = (Collection<OclValue<?>>) analyzedElement.getValue();
        for (final OclValue<?> subElement : collectionValue) {
            flattenRecursive(resultList, subElement);
        }
    }
    
    static int getExpectedSize(Collection<? extends OclValue<?>> source) {
        int size = 0;
        for (final OclValue<?> analyzedElement : source) {
            if (analyzedElement.getType().isCollection()) {
                @SuppressWarnings("unchecked")
                Collection<OclValue<?>> collectionValue = (Collection<OclValue<?>>) analyzedElement.getValue();
                size += getExpectedSize(collectionValue);
            }
            else {
                size++;
            }
        }
        return size;
    }

    public static boolean isFlatten(Iterable<? extends OclValue<?>> collection) {
        for (final OclValue<?> oclValue : collection) {
            if (oclValue.getType().isCollection()) {
                return false;
            }
        }
        return true;
    }
}
