/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.sequence;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllSequenceOperationEvaluators {

    private AllSequenceOperationEvaluators() {
    }
    
    public static ImmutableList<OperationEvaluator> getEvaluators() {
        return ImmutableList.<OperationEvaluator>builder()
                .add(SequenceAppendEvaluator.getInstance())
                .add(SequenceAsBagEvaluator.getInstance())
                .add(SequenceAsOrderedSetEvaluator.getInstance())
                .add(SequenceAsSequenceEvaluator.getInstance())
                .add(SequenceAsSetEvaluator.getInstance())
                .add(SequenceAtEvaluator.getInstance())
                .add(SequenceCountEvaluator.getInstance())
                .add(SequenceExcludingEvaluator.getInstance())
                .add(SequenceFirstEvaluator.getInstance())
                .add(SequenceFlattenEvaluator.getInstance())
                .add(SequenceIncludingEvaluator.getInstance())
                .add(SequenceIndexOfEvaluator.getInstance())
                .add(SequenceInsertAtEvaluator.getInstance())
                .add(SequenceIsEqualEvaluator.getInstance())
                .add(SequenceLastEvaluator.getInstance())
                .add(SequencePrependEvaluator.getInstance())
                .add(SequenceRevertEvaluation.getInstance())
                .add(SequenceSubSequenceEvaluator.getInstance())
                .add(SequenceUnionEvaluator.getInstance())
                
                .build();
    }
}
