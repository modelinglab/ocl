/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.collection;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllCollectionIterators {

    private AllCollectionIterators() {
    }
    
    public static ImmutableList<IteratorEvaluator<?>> getEvaluators() {
        return ImmutableList.<IteratorEvaluator<?>>builder()
                .add(CollectionAnyEvaluator.getInstance())
                .add(CollectionCollectEvaluator.getInstance())
                .add(CollectionExistsEvaluator.getInstance())
                .add(CollectionForAllEvaluator.getInstance())
                .add(CollectionIsUniqueEvaluator.getInstance())
                .add(CollectionOneEvaluator.getInstance())
                .build();
        
        
    }
    
}
