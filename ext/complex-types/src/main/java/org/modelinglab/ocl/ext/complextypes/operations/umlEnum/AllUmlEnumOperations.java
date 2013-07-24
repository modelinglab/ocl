/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.operations.umlEnum;

import java.util.Arrays;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;

/**
 *
 */
public class AllUmlEnumOperations {

    private AllUmlEnumOperations() {
    }

    public static List<Operation> getOperators() {
        return Arrays.asList(new Operation[]{
            new EnumName()
        });
    }
}
