/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.operations;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.SetType;
import java.util.ArrayList;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CollectionOperationsTestHelper {

    /**
     * @param env
     * @param source classifier that extends AnyType
     * <p/>
     * @throws Exception
     */
    public static void test(StaticEnvironment env, CollectionType source) throws Exception {
        ArrayList<Classifier> argumentTypes = new ArrayList(1);

        AnyOperationsTestHelper.test(env, source);

        /*
         * NO ARGUMENTS
         */
        argumentTypes.clear();

        CollectionType resultType = new BagType();
        resultType.setElementType(source.getElementType());

        OperationTestHelper.testOp(
                env,
                source,
                "asBag",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.AsBag.createTemplateOperation());

        resultType = new OrderedSetType();
        resultType.setElementType(source.getElementType());
        OperationTestHelper.testOp(
                env,
                source,
                "asOrderedSet",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.AsOrderedSet.createTemplateOperation());

        resultType = new SequenceType();
        resultType.setElementType(source.getElementType());
        OperationTestHelper.testOp(
                env,
                source,
                "asSequence",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.AsSequence.createTemplateOperation());

        resultType = new SetType();
        resultType.setElementType(source.getElementType());
        OperationTestHelper.testOp(
                env,
                source,
                "asSet",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.AsSet.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "isEmpty",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.IsEmpty.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "notEmpty",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.NotEmpty.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "max",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.Max.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "min",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.Min.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "sum",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.Sum.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "size",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.Size.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "flatten",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.Flatten.createTemplateOperation());

        /**
         * T ARGUMENT
         */
        argumentTypes.clear();
        argumentTypes.add(source.getElementType());

        OperationTestHelper.testOp(
                env,
                source,
                "count",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.Count.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "excludes",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.Excludes.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "includes",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.Includes.createTemplateOperation());

        /**
         * Collection(T) argument
         */
        CollectionType argType = new CollectionType();
        argType.setElementType(source.getElementType());

        argumentTypes.clear();
        argumentTypes.add(argType);

        OperationTestHelper.testOp(
                env,
                source,
                "=",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.IsEqual.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "<>",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.IsDifferent.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "includesAll",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.IncludesAll.createTemplateOperation());

        OperationTestHelper.testOp(
                env,
                source,
                "excludesAll",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.ExcludesAll.createTemplateOperation());

        /**
         * Collection(T2) argument
         */
        argType = new CollectionType();
        argType.setElementType(source); //T2 = Collection(Collection/Bag/Set/etc(T))

        argumentTypes.clear();
        argumentTypes.add(argType);
        OperationTestHelper.testOp(
                env,
                source,
                "product",
                argumentTypes,
                org.modelinglab.ocl.core.standard.operations.collection.Product.createTemplateOperation());

    }
}
