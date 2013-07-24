/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.operations;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OrderedSetOperationsTestHelper {
    
    private OrderedSetOperationsTestHelper() {}
    
    /**
     * @param env 
     * @param source classifier that extends OrderedSetType
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, OrderedSetType source) throws Exception {
        CollectionOperationsTestHelper.test(env, source);
        
        ArrayList<Classifier> intArg = new ArrayList(1);
        intArg.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        
        ArrayList<Classifier> elementType = new ArrayList(1);
        elementType.add(source.getElementType());
        
        ArrayList<Classifier> intAndElementType = new ArrayList(2);
        intAndElementType.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        intAndElementType.add(source.getElementType());
        
        ArrayList<Classifier> twoIntArgs = new ArrayList(2);
        twoIntArgs.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        twoIntArgs.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        
        List<Classifier> emptyArgs = Collections.emptyList();

        OperationTestHelper.testOp(
                env,
                source,
                "append",
                elementType,
                org.modelinglab.ocl.core.standard.operations.orderedSet.Append.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "asBag",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.orderedSet.AsBag.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "asOrderedSet",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.orderedSet.AsOrderedSet.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "asSequence",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.orderedSet.AsSequence.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "asSet",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.orderedSet.AsSet.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "at",
                intArg,
                org.modelinglab.ocl.core.standard.operations.orderedSet.At.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "first",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.orderedSet.First.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "indexOf",
                elementType,
                org.modelinglab.ocl.core.standard.operations.orderedSet.IndexOf.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "insertAt",
                intAndElementType,
                org.modelinglab.ocl.core.standard.operations.orderedSet.InsertAt.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "last",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.orderedSet.Last.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "prepend",
                elementType,
                org.modelinglab.ocl.core.standard.operations.orderedSet.Prepend.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "reverse",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.orderedSet.Reverse.createTemplateOperation());

        OperationTestHelper.testOp(
                env, 
                source, 
                "sum", 
                emptyArgs, 
                org.modelinglab.ocl.core.standard.operations.orderedSet.Sum.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "subOrderedSet",
                twoIntArgs,
                org.modelinglab.ocl.core.standard.operations.orderedSet.SubOrderedSet.createTemplateOperation());
    }
}
