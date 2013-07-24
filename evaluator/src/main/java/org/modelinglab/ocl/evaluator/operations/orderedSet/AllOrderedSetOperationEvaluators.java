/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.orderedSet;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllOrderedSetOperationEvaluators {

    private AllOrderedSetOperationEvaluators() {
    }
    
    public static ImmutableList<OperationEvaluator> getEvaluators() {
        return ImmutableList.<OperationEvaluator>builder()
                .add(OrderedSetAppendEvaluator.getInstance())
                .add(OrderedSetAsBagEvaluator.getInstance())
                .add(OrderedSetAsOrderedSetEvaluator.getInstance())
                .add(OrderedSetAsSequenceEvaluator.getInstance())
                .add(OrderedSetAsSetEvaluator.getInstance())
                .add(OrderedSetAtEvaluator.getInstance())
                .add(OrderedSetFirstEvaluator.getInstance())
                .add(OrderedSetIndexOfEvaluator.getInstance())
                .add(OrderedSetInsertAtEvaluator.getInstance())
                .add(OrderedSetLastEvaluator.getInstance())
                .add(OrderedSetPrependEvaluator.getInstance())
                .add(OrderedSetReverseEvaluator.getInstance())
                .add(OrderedSetSubOrderedSetEvaluator.getInstance())
                
                .build();
    }
}
