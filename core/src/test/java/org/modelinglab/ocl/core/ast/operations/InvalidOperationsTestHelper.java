/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.operations;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.types.InvalidType;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class InvalidOperationsTestHelper {

    private InvalidOperationsTestHelper() {
    }

    /**
     * @param env 
     * @param source classifier that extends InvalidType
     * @throws Exception  
     */
    public static void test(StaticEnvironment env, InvalidType source) throws Exception {
        VoidOperationsTestHelper.test(env, source);
    }
}
