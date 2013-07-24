/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.operations;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class SequenceOperationsTestHelper {
    
    private SequenceOperationsTestHelper() {}
    
    
    /**
     * @param env 
     * @param source classifier that extends SequenceType
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, SequenceType source) throws Exception {
        CollectionOperationsTestHelper.test(env, source);
        
        ArrayList<Classifier> intArg = new ArrayList(1);
        intArg.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        
        ArrayList<Classifier> sequenceType = new ArrayList(1);
        sequenceType.add(source);
        
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
                org.modelinglab.ocl.core.standard.operations.sequence.Append.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "asBag",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.sequence.AsBag.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "asOrderedSet",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.sequence.AsOrderedSet.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "asSequence",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.sequence.AsSequence.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "asSet",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.sequence.AsSet.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "at",
                intArg,
                org.modelinglab.ocl.core.standard.operations.sequence.At.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "count",
                elementType,
                org.modelinglab.ocl.core.standard.operations.sequence.Count.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "excluding",
                elementType,
                org.modelinglab.ocl.core.standard.operations.sequence.Excluding.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "first",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.sequence.First.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "flatten",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.sequence.Flatten.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "including",
                elementType,
                org.modelinglab.ocl.core.standard.operations.sequence.Including.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "indexOf",
                elementType,
                org.modelinglab.ocl.core.standard.operations.sequence.IndexOf.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "insertAt",
                intAndElementType,
                org.modelinglab.ocl.core.standard.operations.sequence.InsertAt.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "=",
                sequenceType,
                org.modelinglab.ocl.core.standard.operations.sequence.IsEqual.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "last",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.sequence.Last.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "prepend",
                elementType,
                org.modelinglab.ocl.core.standard.operations.sequence.Prepend.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "reverse",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.sequence.Reverse.createTemplateOperation());

        
        OperationTestHelper.testOp(
                env, 
                source, 
                "sum", 
                emptyArgs, 
                org.modelinglab.ocl.core.standard.operations.sequence.Sum.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "subSequence",
                twoIntArgs,
                org.modelinglab.ocl.core.standard.operations.sequence.SubSequence.createTemplateOperation());

        
        OperationTestHelper.testOp(
                env,
                source,
                "union",
                sequenceType,
                org.modelinglab.ocl.core.standard.operations.sequence.Union.createTemplateOperation());
    }
}
