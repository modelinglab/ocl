/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.evaluator.umlEnum;

import java.util.Arrays;
import java.util.List;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 */
public class AllUmlEnumEvaluators {

    private AllUmlEnumEvaluators() {
    }

    public static List<OperationEvaluator> getEvaluators() {
        return Arrays.asList(new OperationEvaluator[]{
            new EnumNameEvaluator()
        });
    }

}
