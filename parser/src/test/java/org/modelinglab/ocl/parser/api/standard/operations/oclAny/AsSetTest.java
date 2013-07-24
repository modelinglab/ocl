/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.api.standard.operations.oclAny;

import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.IntegerLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.parser.CompanyEnvironment;
import org.modelinglab.ocl.parser.ParserTesterTool;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class AsSetTest {
    
    @Test
    public void implicitAsSet() throws Exception {
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        final String expressionToTest = "2->size()";
        //expected 2.asSet()->size()
        
        OperationCallExp asSet = new OperationCallExp();
        List<Classifier> emptyArgs = Collections.emptyList();
        Operation asSetOp = env.lookupOperation(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER), "asSet", emptyArgs);
        assert asSetOp != null;
        asSet.setReferredOperation(asSetOp);
        asSet.setSource(new IntegerLiteralExp(2l));
        
        OperationCallExp expected = new OperationCallExp();
        expected.setSource(asSet);
        expected.setReferredOperation(env.lookupOperation(asSet.getType(), "size", emptyArgs));
        
        ParserTesterTool.testParser(expressionToTest, expected, env);
    }
}
