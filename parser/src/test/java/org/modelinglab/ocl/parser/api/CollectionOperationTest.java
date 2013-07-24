/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.api;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.exceptions.AmbiguosOperationCall;
import org.modelinglab.ocl.parser.CompanyEnvironment;
import org.modelinglab.ocl.parser.ParserTesterTool;
import org.modelinglab.ocl.parser.api.standard.operations.umlClass.AllInstancesTest;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CollectionOperationTest {

    @Test
    public void allInstancesTest() throws Exception {
        final String expression = "Person.allInstances()->size()";
        StaticEnvironment env = CompanyEnvironment.createEnvironment();

        OperationCallExp expectedExp = createSize(env, AllInstancesTest.createPersonAllInstances(env));

        ParserTesterTool.testParser(expression, expectedExp, env);
    }
    
    public static OperationCallExp createSize(StaticEnvironment env, OclExpression source) throws AmbiguosOperationCall {
        OperationCallExp result = new OperationCallExp();
        
        /*
         * operation
         */
        List<Classifier> argTypes = Collections.emptyList();
        Operation op = env.lookupOperation(source.getType(), "size", argTypes);
        
        /*
         * source
         */
        //nothing to do
        
        /*
         * arguments
         */
        List<OclExpression> argsExp = Collections.emptyList();
        
        /*
         * result
         */
        result.setSource(source);
        result.setArguments(argsExp);
        result.setReferredOperation(op);
        
        return result;
    }
}
