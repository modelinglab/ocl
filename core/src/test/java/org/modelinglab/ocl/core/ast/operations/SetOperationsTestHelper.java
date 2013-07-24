/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.operations;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.SetType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class SetOperationsTestHelper {
    
    private SetOperationsTestHelper() {}
    
    
    /**
     * @param env 
     * @param source classifier that extends SetType
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, SetType source) throws Exception {
        CollectionOperationsTestHelper.test(env, source);
        
        ArrayList<Classifier> intArg = new ArrayList(1);
        intArg.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        
        ArrayList<Classifier> setType = new ArrayList(1);
        setType.add(source);
        
        ArrayList<Classifier> elementType = new ArrayList(1);
        elementType.add(source.getElementType());
        
        ArrayList<Classifier> bagType = new ArrayList(1);
        BagType bt = new BagType();
        bt.setElementType(source.getElementType());
        bagType.add(bt);
        
        ArrayList<Classifier> twoIntArgs = new ArrayList(2);
        twoIntArgs.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        twoIntArgs.add(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        
        List<Classifier> emptyArgs = Collections.emptyList();

        OperationTestHelper.testOp(
                env,
                source,
                "asBag",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.set.AsBag.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "asOrderedSet",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.set.AsOrderedSet.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "asSequence",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.set.AsSequence.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "asSet",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.set.AsSet.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "count",
                elementType,
                org.modelinglab.ocl.core.standard.operations.set.Count.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "excluding",
                elementType,
                org.modelinglab.ocl.core.standard.operations.set.Excluding.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "flatten",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.set.Flatten.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "including",
                elementType,
                org.modelinglab.ocl.core.standard.operations.set.Including.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "intersection",
                bagType,
                org.modelinglab.ocl.core.standard.operations.set.IntersectionBag.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "intersection",
                setType,
                org.modelinglab.ocl.core.standard.operations.set.IntersectionSet.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "=",
                setType,
                org.modelinglab.ocl.core.standard.operations.set.IsEqual.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env,
                source,
                "-",
                setType,
                org.modelinglab.ocl.core.standard.operations.set.Substraction.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "symmetricDifference",
                setType,
                org.modelinglab.ocl.core.standard.operations.set.SymmetricDifference.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "union",
                bagType,
                org.modelinglab.ocl.core.standard.operations.set.UnionBag.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "union",
                setType,
                org.modelinglab.ocl.core.standard.operations.set.UnionSet.createTemplateOperation());
    }
    
}
