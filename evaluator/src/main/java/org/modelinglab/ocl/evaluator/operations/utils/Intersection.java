/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.operations.utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.ArrayList;
import java.util.Collection;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;

/**
 *
 */
public class Intersection {
    private Intersection() {}
    
    public static SetValue<?> setIntersection(SetValue<? extends OclValue<?>> set, BagValue<? extends OclValue<?>> bag, Classifier elementType) {
        ArrayList<OclValue<?>> result = new ArrayList<>(Math.min(set.getValue().size(), set.getValue().size()));
        
        for (final OclValue<?> object : set.getValue()) {
            if (bag.getValue().contains(object)) {
                result.add(object);
            }
        }
        return new SetValue<>(result, elementType, true);
    }
    
    public static SetValue<?> setIntersection(SetValue<? extends OclValue<?>> set1, SetValue<? extends OclValue<?>> set2, Classifier elementType) {
        ArrayList<OclValue<?>> result = new ArrayList<>(Math.min(set1.getValue().size(), set2.getValue().size()));
        
        for (final OclValue<?> object : set1.getValue()) {
            if (set2.getValue().contains(object)) {
                result.add(object);
            }
        }
        return new SetValue<>(result, elementType, true);
    }
    
    public static BagValue<?> bagIntersection(BagValue<? extends OclValue<?>> bag1, BagValue<? extends OclValue<?>> bag2, Classifier elementType) {
        Multiset<OclValue<?>> multiset1 = HashMultiset.create(bag1.getValue());
        Multiset<OclValue<?>> multiset2 = HashMultiset.create(bag2.getValue());
        ArrayList<OclValue<?>> result = new ArrayList<>(Math.min(bag1.getValue().size(), bag2.getValue().size()));
        
        for (final OclValue<?> element : multiset1.elementSet()) {
            if (multiset2.contains(element)) {
                final int count = Math.min(multiset1.count(element), multiset2.count(element));
                for (int i = 0; i < count; i++) {
                    result.add(element);
                }
            }
        }
        
        return new BagValue<>(result, elementType, true);
    }
}
