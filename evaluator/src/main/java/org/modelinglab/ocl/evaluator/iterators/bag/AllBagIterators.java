/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.bag;

import org.modelinglab.ocl.evaluator.iterators.collection.*;
import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllBagIterators {

    private AllBagIterators() {
    }
    
    public static ImmutableList<IteratorEvaluator<?>> getEvaluators() {
        return ImmutableList.<IteratorEvaluator<?>>builder()
                .add(BagCollectEvaluator.getInstance())
                .add(BagCollectNestedEvaluator.getInstance())
                .add(BagRejectEvaluator.getInstance())
                .add(BagSelectEvaluator.getInstance())
                .add(BagSortedByEvaluator.getInstance())
                .build();
        
        
    }
    
}
