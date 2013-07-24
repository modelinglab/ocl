/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.HashSet;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;

/**
 *
 */
public class Union {

    private Union() {
    }

    public static <E1, E2> BagValue<OclValue<?>> bagUnion(BagValue<?> bag1, BagValue<?> bag2, Classifier elementType) {
        int expectedSize = Math.max(bag1.getValue().size(), bag2.getValue().size());
        
        Multiset<OclValue<?>> multiset = HashMultiset.create(expectedSize);
        multiset.addAll(bag1.getValue());
        multiset.addAll(bag2.getValue());
        
        return new BagValue<>(multiset, elementType);
    }
    
    public static SetValue<OclValue<?>> setUnion(SetValue<?> set1, SetValue<?> set2, Classifier elementType) {
        int expectedSize = Math.max(set1.getValue().size(), set2.getValue().size());
        
        HashSet<OclValue<?>> resultSet = new HashSet<>(expectedSize);
        resultSet.addAll(set1.getValue());
        resultSet.addAll(set2.getValue());
        return new SetValue<>(resultSet, elementType);
    }
}
