/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.operations;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.Classifier;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class UmlClassOperationsTestHelper {
    
    private UmlClassOperationsTestHelper() {
    }
    
    /**
     * @param env 
     * @param source
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, UmlClass source) throws Exception {
        AnyOperationsTestHelper.test(env, source);
        
        List<Classifier> emptyArgs = Collections.emptyList();
        
        OperationTestHelper.testOp(
                env,
                source.getClassifierType(),
                "allInstances",
                emptyArgs,
                org.modelinglab.ocl.core.standard.operations.umlClass.AllInstances.createTemplateOperation());
    }
}
