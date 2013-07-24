/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.orderedSet;

import org.modelinglab.ocl.evaluator.iterators.bag.*;
import org.modelinglab.ocl.evaluator.iterators.collection.*;
import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllOrderedSetIterators {

    private AllOrderedSetIterators() {
    }
    
    public static ImmutableList<IteratorEvaluator<?>> getEvaluators() {
        return ImmutableList.<IteratorEvaluator<?>>builder()
                .add(OrderedSetCollectEvaluator.getInstance())
                .add(OrderedSetCollectNestedEvaluator.getInstance())
                .add(OrderedSetRejectEvaluator.getInstance())
                .add(OrderedSetSelectEvaluator.getInstance())
                .add(OrderedSetSortedByEvaluator.getInstance())
                .build();
        
        
    }
    
}
