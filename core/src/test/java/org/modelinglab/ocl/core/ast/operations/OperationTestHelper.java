/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.operations;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.exceptions.AmbiguosOperationCall;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class OperationTestHelper {

    private OperationTestHelper() {
    }

    public static void testOp(
            StaticEnvironment env, 
            Classifier source, 
            String opName, 
            List<Classifier> args, 
            Operation expectedOperation) throws AmbiguosOperationCall {
        
        Operation op = env.lookupOperation(source, opName, args);
        assert op != null;
        
        TemplateRestrictions restrictions = new TemplateRestrictions();
        source.fixTemplates(restrictions);
        
        assert 
                op.overrides(expectedOperation, restrictions)
                : op.getSignature() +" does not overrides expected operation ("+expectedOperation.getSignature()+")";
    }
}
