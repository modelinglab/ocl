/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.real;

import com.google.common.collect.ImmutableList;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.evaluator.operations.bool.BooleanAndEvaluator;
import org.modelinglab.ocl.evaluator.operations.bool.BooleanImpliesEvaluator;
import org.modelinglab.ocl.evaluator.operations.bool.BooleanNotEvaluator;
import org.modelinglab.ocl.evaluator.operations.bool.BooleanOrEvaluator;
import org.modelinglab.ocl.evaluator.operations.bool.BooleanToStringEvaluator;
import org.modelinglab.ocl.evaluator.operations.bool.BooleanXorEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllRealOperationEvaluators {

    private AllRealOperationEvaluators() {
    }
    
    public static ImmutableList<OperationEvaluator> getEvaluators() {
        return ImmutableList.<OperationEvaluator>builder()
                .add(new RealAbsEvaluator())
                .add(new RealAdditionEvaluator())
                .add(new RealDivisionEvaluator())
                .add(new RealFloorEvaluator())
                .add(new RealGreaterEvaluator())
                .add(new RealGreaterOrEqualEvaluator())
                .add(new RealLessEvaluator())
                .add(new RealLessOrEqualEvaluator())
                .add(new RealMaxEvaluator())
                .add(new RealMinEvaluator())
                .add(new RealMultiplicationEvaluator())
                .add(new RealNegativeEvaluator())
                .add(new RealSubstractionEvaluator())
                .add(new RealToStringEvaluator())
                
                .build();
    }
}
