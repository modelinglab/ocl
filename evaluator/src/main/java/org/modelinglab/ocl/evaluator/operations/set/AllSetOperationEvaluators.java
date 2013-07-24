/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.set;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllSetOperationEvaluators {

    private AllSetOperationEvaluators() {
    }
    
    public static ImmutableList<OperationEvaluator> getEvaluators() {
        return ImmutableList.<OperationEvaluator>builder()
                .add(SetAsBagEvaluator.getInstance())
                .add(SetAsOrderedSetEvaluator.getInstance())
                .add(SetAsSequenceEvaluator.getInstance())
                .add(SetAsSetEvaluator.getInstance())
                .add(SetCountEvaluator.getInstance())
                .add(SetExcludingEvaluator.getInstance())
                .add(SetFlattenEvaluator.getInstance())
                .add(SetIncludingEvaluator.getInstance())
                .add(SetIntersectionBagEvaluator.getInstance())
                .add(SetIntersectionSetEvaluator.getInstance())
                .add(SetIsEqualEvaluator.getInstance())
                .add(SetSubstractionEvaluator.getInstance())
                .add(SetSymmetricDifferenceEvaluator.getInstance())
                .add(SetUnionBagEvaluator.getInstance())
                .add(SetUnionSetEvaluator.getInstance())
                
                .build();
    }
}
