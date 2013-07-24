/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.evaluator.string;

import java.util.Arrays;
import java.util.List;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 */
public class AllStringEvaluators {

    private AllStringEvaluators() {
    }

    public static List<OperationEvaluator> getEvaluators() {
        return Arrays.asList(new OperationEvaluator[]{
            new StringContainsEvaluator(),
            new StringEndsWithEvaluator(),
            new StringStartsWithEvaluator()
        });
    }
}
