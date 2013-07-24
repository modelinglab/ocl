/*
 * To change this template).add( choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.collection;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllCollectionOperationEvaluators {

    private AllCollectionOperationEvaluators() {
    }

    public static ImmutableList<OperationEvaluator> getEvaluators() {
        return ImmutableList.<OperationEvaluator>builder()
                .add(CollectionAsBagEvaluator.getInstance())
                .add(CollectionAsOrderedSetEvaluator.getInstance())
                .add(CollectionAsSequenceEvaluator.getInstance())
                .add(CollectionAsSetEvaluator.getInstance())
                .add(CollectionCountEvaluator.getInstance())
                .add(CollectionExcludesAllEvaluator.getInstance())
                .add(CollectionExcludesEvaluator.getInstance())
                .add(CollectionFlattenEvaluator.getInstance())
                .add(CollectionIncludesAllEvaluator.getInstance())
                .add(CollectionIncludesEvaluator.getInstance())
                .add(CollectionIsDifferentEvaluator.getInstance())
                .add(CollectionIsEmptyEvaluator.getInstance())
                .add(CollectionIsEqualEvaluator.getInstance())
                .add(CollectionMaxEvaluator.getInstance())
                .add(CollectionMinEvaluator.getInstance())
                .add(CollectionNotEmptyEvaluator.getInstance())
                .add(CollectionProductEvaluator.getInstance())
                .add(CollectionSizeEvaluator.getInstance())
                .add(CollectionSumEvaluator.getInstance())
                .build();
    }
}
