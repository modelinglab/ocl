/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.operations;

import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import java.util.ArrayList;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class AnyOperationsTestHelper {

    private AnyOperationsTestHelper() {
    }
    
    /**
     * @param env 
     * @param source classifier that extends AnyType
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, Classifier source) throws Exception {
        ArrayList<Classifier> argumentTypes = new ArrayList(1);
        
        /**
         * ANY_TYPE ARGUMENT
         */
        argumentTypes.clear();
        argumentTypes.add(AnyType.getInstance());
        OperationTestHelper.testOp(
                env, 
                source, 
                "=", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.oclAny.IsEqual.getInstance());
        OperationTestHelper.testOp(
                env, 
                source, 
                "<>", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.oclAny.IsDifferent.getInstance());
        
        /*
         * NO ARGUMENTS
         */
        argumentTypes.clear();
        
        OperationTestHelper.testOp(
                env, 
                source, 
                "oclIsUndefined", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.oclAny.OclIsUndefined.getInstance());
        OperationTestHelper.testOp(
                env, 
                source, 
                "oclIsInvalid", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.oclAny.OclIsInvalid.getInstance());
        
        /**
         * TYPE ARGUMENT
         */
        ClassifierType castType = UmlClass.getGenericInstance().getClassifierType();
        CollectionType collectionType = new OrderedSetType();
        collectionType.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        argumentTypes.clear();
        argumentTypes.add(castType);
        
        OperationTestHelper.testOp(
                env, 
                source, 
                "oclIsKindOf", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.oclAny.OclIsKindOf.createTemplateOperation());
        OperationTestHelper.testOp(
                env, 
                source, 
                "oclIsTypeOf", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.oclAny.OclIsTypeOf.createTemplateOperation());
        OperationTestHelper.testOp(
                env, 
                source, 
                "oclAsType", 
                argumentTypes, 
                org.modelinglab.ocl.core.standard.operations.oclAny.OclAsType.createTemplateOperation());
    }
}
