/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.sequence;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllSequenceIterators {

    private AllSequenceIterators() {
    }
    
    public static ImmutableList<IteratorEvaluator<?>> getEvaluators() {
        return ImmutableList.<IteratorEvaluator<?>>builder()
                .add(SequenceCollectEvaluator.getInstance())
                .add(SequenceCollectNestedEvaluator.getInstance())
                .add(SequenceRejectEvaluator.getInstance())
                .add(SequenceSelectEvaluator.getInstance())
                .add(SequenceSortedByEvaluator.getInstance())
                .build();
        
        
    }
    
}
