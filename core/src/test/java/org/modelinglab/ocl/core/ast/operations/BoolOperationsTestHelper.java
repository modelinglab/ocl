/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.operations;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class BoolOperationsTestHelper {
    
    private BoolOperationsTestHelper() {}
    
    /**
     * @param env 
     * @param source classifier that extends BooleanType
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, PrimitiveType source) throws Exception {
        assert source.getPrimitiveKind() == PrimitiveType.PrimitiveKind.BOOLEAN;
        
        AnyOperationsTestHelper.test(env, source);
        
        ArrayList<Classifier> booleanArgument = new ArrayList(1);
        booleanArgument.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        List<Classifier> emptyArgs = Collections.emptyList();
       
        
        OperationTestHelper.testOp(
                env,
                source,
                "and",
                booleanArgument,
                org.modelinglab.ocl.core.standard.operations.bool.And.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "implies",
                booleanArgument,
                org.modelinglab.ocl.core.standard.operations.bool.Implies.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "not",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.bool.Not.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "or",
                booleanArgument,
                org.modelinglab.ocl.core.standard.operations.bool.Or.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "toString",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.bool.ToString.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "xor",
                booleanArgument,
                org.modelinglab.ocl.core.standard.operations.bool.Xor.getInstance());
    }
}
