/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.set;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllSetIterators {

    private AllSetIterators() {
    }
    
    public static ImmutableList<IteratorEvaluator<?>> getEvaluators() {
        return ImmutableList.<IteratorEvaluator<?>>builder()
                .add(SetCollectEvaluator.getInstance())
                .add(SetCollectNestedEvaluator.getInstance())
                .add(SetRejectEvaluator.getInstance())
                .add(SetSelectEvaluator.getInstance())
                .add(SetSortedByEvaluator.getInstance())
                .build();
        
        
    }
    
}
