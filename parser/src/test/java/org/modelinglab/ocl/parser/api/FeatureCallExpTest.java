/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.api;

import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.standard.operations.umlClass.AllInstances;
import org.modelinglab.ocl.parser.CompanyEnvironment;
import org.modelinglab.ocl.parser.ParserTesterTool;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class FeatureCallExpTest {
    
    @Test
    public void implicitAttributeTest() throws Exception {
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        
        ParserTesterTool.addSelf(env, "Person");
        
        AttributeCallExp expectedExp = new AttributeCallExp();
        
        Attribute att = env.lookupImplicitAttribute("name");
        assert att.getName().equals("name");
        assert att.getUmlClass().getName().equals("Person");
        
        expectedExp.setReferredAttribute(att);
        
        VariableExp varExp = new VariableExp();
        varExp.setReferredVariable((Variable)env.findImplicitSourceForAttribute("name"));
        expectedExp.setSource(varExp);
        
        ParserTesterTool.testParser("name", expectedExp, env);
        
        ParserTesterTool.removeSelf(env);
    }
    
    @Test
    public void allInstancesTest() throws Exception {
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        
        ParserTesterTool.addSelf(env, "Person");
                
        OperationCallExp expectedExp = new OperationCallExp();
        UmlClass personClazz = (UmlClass) env.lookup("Person");
        List<Classifier> argsTypes = Collections.emptyList();
        expectedExp.setReferredOperation(
                AllInstances.createTemplateOperation().specificOperation(
                    personClazz.getClassifierType(), argsTypes, new TemplateRestrictions()));
        List<OclExpression> args = Collections.emptyList();
        expectedExp.setArguments(args);
        
        expectedExp.setSource(ParserTesterTool.parse("Person", env));
        
        ParserTesterTool.testParser("Person.allInstances()", expectedExp, env);
        
        ParserTesterTool.removeSelf(env);
    }
    
    
}
