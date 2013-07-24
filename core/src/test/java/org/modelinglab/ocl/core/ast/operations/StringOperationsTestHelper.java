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
public class StringOperationsTestHelper {
    
    private StringOperationsTestHelper() {}
    
    /**
     * @param env 
     * @param source classifier that extends StringType
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, PrimitiveType source) throws Exception {
        assert source.getPrimitiveKind() == PrimitiveType.PrimitiveKind.STRING;
        
        AnyOperationsTestHelper.test(env, source);
        
        ArrayList<Classifier> stringArg = new ArrayList(1);
        stringArg.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        
        ArrayList<Classifier> intArg = new ArrayList(1);
        intArg.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        
        ArrayList<Classifier> twoIntArgs = new ArrayList(2);
        twoIntArgs.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        twoIntArgs.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
                
        List<Classifier> emptyArgs = Collections.emptyList();
               
        OperationTestHelper.testOp(
                env,
                source,
                "at",
                intArg,
                org.modelinglab.ocl.core.standard.operations.string.At.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "characters",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.string.Characters.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "concat",
                stringArg,
                org.modelinglab.ocl.core.standard.operations.string.Concat.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "+",
                stringArg,
                org.modelinglab.ocl.core.standard.operations.string.Addition.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "equalsIgnoreCase",
                stringArg,
                org.modelinglab.ocl.core.standard.operations.string.EqualsIgnoreCase.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                ">",
                stringArg,
                org.modelinglab.ocl.core.standard.operations.string.Greater.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                ">=",
                stringArg,
                org.modelinglab.ocl.core.standard.operations.string.GreaterOrEqual.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "indexOf",
                stringArg,
                org.modelinglab.ocl.core.standard.operations.string.IndexOf.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "<",
                stringArg,
                org.modelinglab.ocl.core.standard.operations.string.Less.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "<=",
                stringArg,
                org.modelinglab.ocl.core.standard.operations.string.LessOrEqual.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "size",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.string.Size.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "substring",
                twoIntArgs,
                org.modelinglab.ocl.core.standard.operations.string.Substring.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "toBoolean",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.string.ToBoolean.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "toInteger",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.string.ToInteger.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "toLowerCase",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.string.ToLowerCase.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "toReal",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.string.ToReal.getInstance());
        
        OperationTestHelper.testOp(
                env,
                source,
                "toUpperCase",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.string.ToUpperCase.getInstance());
    }
}
