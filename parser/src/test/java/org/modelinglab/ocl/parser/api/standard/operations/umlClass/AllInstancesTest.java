/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.api.standard.operations.umlClass;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.expressions.TypeExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.exceptions.AmbiguosOperationCall;
import org.modelinglab.ocl.parser.CompanyEnvironment;
import org.modelinglab.ocl.parser.ParserTesterTool;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class AllInstancesTest {
    
    @Test
    public void allInstancesTest() throws Exception {
        final String expression = "Person.allInstances()";
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        
        OperationCallExp expectedExp = createPersonAllInstances(env);
        
        ParserTesterTool.testParser(expression, expectedExp, env);
    }
    
    public static OperationCallExp createPersonAllInstances(StaticEnvironment env) throws AmbiguosOperationCall {
        /*
         * operation
         */
        UmlClass personClass = (UmlClass) env.lookup("Person");
        List<Classifier> argTypes = Collections.emptyList();
        Operation op = env.lookupOperation(personClass.getClassifierType(), "allInstances", argTypes);
        assert op != null;
        
        /*
         * source
         */
        TypeExp source = personClass.getClassifierType().createExpression();
        
        /*
         * arguments
         */
        List<OclExpression> argsExp = Collections.emptyList();
        
        /*
         * expectedExp
         */
        OperationCallExp expectedExp = new OperationCallExp();
        expectedExp.setReferredOperation(op);
        expectedExp.setSource(source);        
        expectedExp.setArguments(argsExp);
        
        return expectedExp;
    }
}
