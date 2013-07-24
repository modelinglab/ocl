/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllOclAnyOperationEvaluators {

    private AllOclAnyOperationEvaluators() {
    }
    
    public static ImmutableList<OperationEvaluator> getEvaluators() {
        return ImmutableList.<OperationEvaluator>builder()
                .add(new OclAnyAsSetEvaluator())
                .add(new OclAnyIsDifferentEvaluator())
                .add(new OclAnyIsEqualEvaluator())
                .add(new OclAnyOclAsTypeEvaluator())
                .add(new OclAnyOclIsInvalidEvaluator())
                .add(new OclAnyOclIsKindOfEvaluator())
                .add(new OclAnyOclIsTypeOfEvaluator())
                .add(new OclAnyOclIsUndefinedEvaluator())
                .add(new OclAnyOclTypeEvaluator())
                
                .build();
    }
}
