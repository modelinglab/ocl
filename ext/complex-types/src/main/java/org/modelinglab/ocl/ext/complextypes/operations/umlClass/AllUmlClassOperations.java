/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.operations.umlClass;

import java.util.Arrays;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;

/**
 *
 */
public class AllUmlClassOperations {
    private AllUmlClassOperations() {}
    
    public static List<Operation> getOperators() {
        return Arrays.asList(new Operation[] {
                new ClassToString()
        });
    }
}
