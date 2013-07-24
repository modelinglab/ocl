/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.bool;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllBooleanOperationEvaluators {

    private AllBooleanOperationEvaluators() {
    }
    
    public static ImmutableList<OperationEvaluator> getEvaluators() {
        return ImmutableList.<OperationEvaluator>builder()
                .add(new BooleanAndEvaluator())
                .add(new BooleanImpliesEvaluator())
                .add(new BooleanNotEvaluator())
                .add(new BooleanOrEvaluator())
                .add(new BooleanToStringEvaluator())
                .add(new BooleanXorEvaluator())
                .build();
    }
}
