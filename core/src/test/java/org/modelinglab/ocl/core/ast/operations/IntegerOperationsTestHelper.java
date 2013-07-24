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
public class IntegerOperationsTestHelper {

    private IntegerOperationsTestHelper() {
    }
    
    /**
     * @param env 
     * @param source classifier that extends IntegerType
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, PrimitiveType source) throws Exception {
        assert source.getPrimitiveKind() == PrimitiveType.PrimitiveKind.INTEGER;
        
        AnyOperationsTestHelper.test(env, source);
        
        ArrayList<Classifier> intArgument = new ArrayList(1);
        intArgument.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        
        List<Classifier> emptyArgs = Collections.emptyList();
       
        
        OperationTestHelper.testOp(
                env,
                source,
                "abs",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.integer.Abs.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "+",
                intArgument,
                org.modelinglab.ocl.core.standard.operations.integer.Addition.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "/",
                intArgument,
                org.modelinglab.ocl.core.standard.operations.integer.Division.getInstance());
        
        
        OperationTestHelper.testOp(
                env,
                source,
                "div",
                intArgument,
                org.modelinglab.ocl.core.standard.operations.integer.IntegerDivision.getInstance());
        
        
        OperationTestHelper.testOp(
                env,
                source,
                "max",
                intArgument,
                org.modelinglab.ocl.core.standard.operations.integer.Max.getInstance());
        
        
        OperationTestHelper.testOp(
                env,
                source,
                "min",
                intArgument,
                org.modelinglab.ocl.core.standard.operations.integer.Min.getInstance());
        
        
        OperationTestHelper.testOp(
                env,
                source,
                "mod",
                intArgument,
                org.modelinglab.ocl.core.standard.operations.integer.Mod.getInstance());
        
        
        OperationTestHelper.testOp(
                env,
                source,
                "*",
                intArgument,
                org.modelinglab.ocl.core.standard.operations.integer.Multiplication.getInstance());
        
        
        OperationTestHelper.testOp(
                env,
                source,
                "-",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.integer.Negative.getInstance());
        
        
        OperationTestHelper.testOp(
                env,
                source,
                "-",
                intArgument,
                org.modelinglab.ocl.core.standard.operations.integer.Substraction.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "toString",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.integer.ToString.getInstance());
    }
}
