/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllStringOperationEvaluators {

    private AllStringOperationEvaluators() {
    }
    
    public static ImmutableList<OperationEvaluator> getEvaluators() {
        return ImmutableList.<OperationEvaluator>builder()
                .add(new StringAdditionEvaluator())
                .add(new StringAtEvaluator())
                .add(new StringCharactersEvaluator())
                .add(new StringConcatEvaluator())
                .add(new StringEqualsIgnoreCaseEvaluator())
                .add(new StringGreaterEvaluator())
                .add(new StringGreaterOrEqualEvaluator())
                .add(new StringIndexOfEvaluator())
                .add(new StringLessEvaluator())
                .add(new StringLessOrEqualEvaluator())
                .add(new StringSizeEvaluator())
                .add(new StringSubstringEvaluator())
                .add(new StringToBooleanEvaluator())
                .add(new StringToIntegerEvaluator())
                .add(new StringToLowerCaseEvaluator())
                .add(new StringToRealEvaluator())
                .add(new StringToUpperCaseEvaluator())
                
                .build();
    }
}
