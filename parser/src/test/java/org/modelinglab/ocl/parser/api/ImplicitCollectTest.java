/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.api;

import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.parser.CompanyEnvironment;
import org.modelinglab.ocl.parser.ParserTesterTool;
import org.modelinglab.ocl.parser.api.standard.operations.umlClass.AllInstancesTest;
import org.modelinglab.ocl.parser.walker.pojos.ItVariableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ImplicitCollectTest {
    
    @Test
    public void allInstancesAttTest() throws Exception {
        final String expression = "Person.allInstances().name";
        StaticEnvironment env = CompanyEnvironment.createEnvironment();

        UmlClass personType = (UmlClass) env.lookup("Person");
        
        /*
         * itVars
         */
        Variable itVar = new Variable("", null, personType);
        ItVariableList itVars = new ItVariableList(1);
        itVars.add(itVar);
        
        /*
         * body
         */
        OclExpression source = AllInstancesTest.createPersonAllInstances(env);
        assert personType != null;
        Attribute nameAtt = null;
        for (Attribute attribute : personType.getAllAttributes()) {
            if (attribute.getName().equals("name")) {
                nameAtt = attribute;
                break;
            }
        }
        assert nameAtt != null;
        AttributeCallExp body = new AttributeCallExp();
        body.setReferredAttribute(nameAtt);
        VariableExp varExp = new VariableExp(itVar.clone());
        body.setSource(varExp);
        
        /*
         * expected result
         */
        IteratorExp expectedExp = env.lookupIterator((CollectionType) source.getType(), "collect", body.getType());
        expectedExp.setSource(source);
        expectedExp.setBody(body);
        expectedExp.setIterators(itVars.clone());
        
        ParserTesterTool.testParser(expression, expectedExp, env);
    }
    
    @Test
    public void allInstancesOpTest() throws Exception {
        final String expression = "Person.allInstances().opWithoutArgs()";
        StaticEnvironment env = CompanyEnvironment.createEnvironment();

        UmlClass personType = (UmlClass) env.lookup("Person");
        
        /*
         * itVars
         */
        Variable itVar = new Variable("", null, personType);
        List<Variable> itVars = new ArrayList<Variable>(1);
        itVars.add(itVar);
        
        /*
         * body
         */
        OclExpression source = AllInstancesTest.createPersonAllInstances(env);
        assert personType != null;
        List<Classifier> argTypes = Collections.emptyList();
        Operation op = env.lookupOperation(personType, "opWithoutArgs", argTypes);
        assert op != null;
        OperationCallExp body = new OperationCallExp();
        body.setReferredOperation(op);
        VariableExp varExp = new VariableExp(itVar);
        body.setSource(varExp);
        List<OclExpression> argValues = Collections.emptyList();
        body.setArguments(argValues);
        
        /*
         * expected result
         */
        IteratorExp expectedExp = env.lookupIterator((CollectionType) source.getType(), "collect", body.getType());
        expectedExp.setSource(source);
        expectedExp.setBody(body);
        expectedExp.setIterators(itVars);
        
        ParserTesterTool.testParser(expression, expectedExp, env);
    }
}
