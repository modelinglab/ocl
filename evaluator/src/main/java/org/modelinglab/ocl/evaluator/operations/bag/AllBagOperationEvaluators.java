/*
 * To change this template.add( choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bag;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllBagOperationEvaluators {

    private AllBagOperationEvaluators() {
    }

    public static ImmutableList<OperationEvaluator> getEvaluators() {
        return ImmutableList.<OperationEvaluator>builder()
                .add(BagAsBagEvaluator.getInstance())
                .add(BagAsOrderedSetEvaluator.getInstance())
                .add(BagAsSequenceEvaluator.getInstance())
                .add(BagAsSetEvaluator.getInstance())
                .add(BagCountEvaluator.getInstance())
                .add(BagExcludingEvaluator.getInstance())
                .add(BagFlattenEvaluator.getInstance())
                .add(BagIncludingEvaluator.getInstance())
                .add(BagIntersectionBagEvaluator.getInstance())
                .add(BagIntersectionSetEvaluator.getInstance())
                .add(BagIsEqualEvaluator.getInstance())
                .add(BagUnionBagEvaluator.getInstance())
                .add(BagUnionSetEvaluator.getInstance())
                .build();
    }

}
