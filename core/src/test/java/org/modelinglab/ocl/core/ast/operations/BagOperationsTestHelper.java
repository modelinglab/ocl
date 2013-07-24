/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.operations;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.SetType;
import java.util.ArrayList;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class BagOperationsTestHelper {

    private BagOperationsTestHelper() {
    }
    
    /**
     * @param env 
     * @param source classifier that extends BagType
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, CollectionType source) throws Exception {
        ArrayList<Classifier> argumentTypes = new ArrayList(1);
        
        CollectionOperationsTestHelper.test(env, source);
        
        /*
         * NO ARGUMENTS
         */
        argumentTypes.clear();
        
        OperationTestHelper.testOp(
                env, 
                source, 
                "asBag", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.AsBag.createTemplateOperation());

        OperationTestHelper.testOp(
                env, 
                source, 
                "asOrderedSet", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.AsOrderedSet.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env, 
                source, 
                "asSequence", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.AsSequence.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env, 
                source, 
                "asSet", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.AsSet.createTemplateOperation());
        OperationTestHelper.testOp(
                env, 
                source, 
                "flatten", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.Flatten.createTemplateOperation());
        
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
                org.modelinglab.ocl.core.standard.operations.bag.Count.createTemplateOperation());
        OperationTestHelper.testOp(
                env, 
                source, 
                "excluding", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.Excluding.createTemplateOperation());
        OperationTestHelper.testOp(
                env, 
                source, 
                "including", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.Including.createTemplateOperation());
        
        /**
         * Bag(T) argument
         */
        
        BagType bt = new BagType();
        bt.setElementType(source.getElementType());
        
        argumentTypes.clear();
        argumentTypes.add(bt);
        
        OperationTestHelper.testOp(
                env, 
                source, 
                "=", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.IsEqual.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env, 
                source, 
                "intersection",
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.IntersectionBag.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env, 
                source, 
                "union", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.UnionBag.createTemplateOperation());
        
        /**
         * Set(T) argument
         */
        SetType st = new SetType();
        st.setElementType(source.getElementType());
        
        argumentTypes.clear();
        argumentTypes.add(st);
        
        OperationTestHelper.testOp(
                env, 
                source, 
                "intersection", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.IntersectionSet.createTemplateOperation());
        
        OperationTestHelper.testOp(
                env, 
                source, 
                "union", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.bag.UnionSet.createTemplateOperation());
        
        
    }
}
