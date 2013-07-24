/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.operations;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import java.util.ArrayList;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class VoidOperationsTestHelper {
    
    private VoidOperationsTestHelper() {}
    
    /**
     * @param env 
     * @param source classifier that extends VoidType
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, Classifier source) throws Exception {
        AnyOperationsTestHelper.test(env, source);
        
        ArrayList<Classifier> aArgument = new ArrayList(1);
        aArgument.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        OperationTestHelper.testOp(
                env,
                source,
                "=",
                aArgument,
                org.modelinglab.ocl.core.standard.operations.oclVoid.IsEqual.getInstance());
    }
}
