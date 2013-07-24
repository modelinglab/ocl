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
public class RealOperationsTestHelper {
    
    private RealOperationsTestHelper() {}
    
    /**
     * @param env 
     * @param source classifier that extends RealType
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, PrimitiveType source) throws Exception {
        assert source.getPrimitiveKind() == PrimitiveType.PrimitiveKind.REAL;
        
        AnyOperationsTestHelper.test(env, source);
        
        ArrayList<Classifier> realArgument = new ArrayList(1);
        realArgument.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL));
        
        List<Classifier> emptyArgs = Collections.emptyList();
               
        OperationTestHelper.testOp(
                env,
                source,
                "abs",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.real.Abs.getInstance());

        OperationTestHelper.testOp(
                env,
                source,
                "+",
                realArgument,
                org.modelinglab.ocl.core.standard.operations.real.Addition.getInstance());

        OperationTestHelper.testOp(
                env,
                source,
                "/",
                realArgument,
                org.modelinglab.ocl.core.standard.operations.real.Division.getInstance());

        OperationTestHelper.testOp(
                env,
                source,
                "floor",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.real.Floor.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "round",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.real.Round.getInstance());

        OperationTestHelper.testOp(
                env,
                source,
                ">",
                realArgument,
                org.modelinglab.ocl.core.standard.operations.real.Greater.getInstance());
                
        OperationTestHelper.testOp(
                env,
                source,
                ">=",
                realArgument,
                org.modelinglab.ocl.core.standard.operations.real.GreaterOrEqual.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "<",
                realArgument,
                org.modelinglab.ocl.core.standard.operations.real.Less.getInstance());
                
        OperationTestHelper.testOp(
                env,
                source,
                "<=",
                realArgument,
                org.modelinglab.ocl.core.standard.operations.real.LessOrEqual.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "max",
                realArgument,
                org.modelinglab.ocl.core.standard.operations.real.Max.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "min",
                realArgument,
                org.modelinglab.ocl.core.standard.operations.real.Min.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "*",
                realArgument,
                org.modelinglab.ocl.core.standard.operations.real.Multiplication.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "-",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.real.Negative.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "-",
                realArgument,
                org.modelinglab.ocl.core.standard.operations.real.Substraction.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "toString",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.real.ToString.getInstance());
        
    }
}
