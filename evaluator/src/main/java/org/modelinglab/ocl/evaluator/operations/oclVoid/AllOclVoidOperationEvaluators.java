/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclVoid;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllOclVoidOperationEvaluators {

    private AllOclVoidOperationEvaluators() {
    }
    
    public static ImmutableList<OperationEvaluator> getEvaluators() {
        return ImmutableList.<OperationEvaluator>builder()
                .add(new OclVoidIsEqualEvaluator())
                
                .build();
    }
}
