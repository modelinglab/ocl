/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.evaluator.umlClass;

import java.util.Arrays;
import java.util.List;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 */
public class AllUmlClassEvaluators {

    private AllUmlClassEvaluators() {
    }

    public static List<OperationEvaluator> getEvaluators() {
        return Arrays.asList(new OperationEvaluator[]{
            new ClassToStringEvaluator()
        });
    }
}
