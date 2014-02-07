/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.operations.string;

import java.util.Arrays;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class AllStringOperations {
    private AllStringOperations() {}
    
    public static List<Operation> getOperators() {
        return Arrays.asList(new Operation[] {
                StringContains.getInstance(),
                StringEndsWith.getInstance(),
                StringSplit.getInstance(),
                StringStartsWith.getInstance(),
        });
    }
}
