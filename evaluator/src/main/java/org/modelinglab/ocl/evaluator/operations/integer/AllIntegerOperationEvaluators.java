/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.integer;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllIntegerOperationEvaluators {

    private AllIntegerOperationEvaluators() {
    }
    
    public static ImmutableList<OperationEvaluator> getEvaluators() {
        return ImmutableList.<OperationEvaluator>builder()
                .add(new IntegerAbsEvaluator())
                .add(new IntegerAdditionEvaluator())
                .add(new IntegerDivisionEvaluator())
                .add(new IntegerIntegerDivisionEvaluator())
                .add(new IntegerMaxEvaluator())
                .add(new IntegerMinEvaluator())
                .add(new IntegerModEvaluator())
                .add(new IntegerMultiplicationEvaluator())
                .add(new IntegerNegativeEvaluator())
                .add(new IntegerSubstractionEvaluator())
                .add(new IntegerToStringEvaluator())
                .build();
    }
}
