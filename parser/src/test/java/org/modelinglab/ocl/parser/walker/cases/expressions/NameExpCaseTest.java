/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.NameExpCase;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.walker.pojos.UndeclaredVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class NameExpCaseTest extends CaseTestAbstract {

    @Before
    public void addEmployee() {
        addSelf("Employee");
    }

    @After
    public void removeEmployee() {
        removeSelf();
    }
    
    @Test
    public void testOut_Default() {
        String tokenName = "asd";
        
        try {
            invokeOut(tokenName, null);
            assert false : "default case should fail when element is not valid and production is a "
                    + "iterator variable declaration";
        }
        catch (OclParserException ex) {
        }
    }
    
    @Test
    public void testOut_Classifier() {
        String tokenName = "Person";
        
        Classifier expectedType = (Classifier) env.lookup("Person");
        assert expectedType != null;
        TypeExp expected = expectedType.getClassifierType().createExpression();
        
        invokeOut(tokenName, expected);
        
        tokenName = "Integer";
        
        expectedType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER);
        assert expectedType != null;
        expected = expectedType.getClassifierType().createExpression();
        
        invokeOut(tokenName, expected);
    }
    
    @Test
    public void testOut_Iterator() {
        String tokenName = "it";
        
        Variable var = new Variable(tokenName);
        VariableExp expectedResult = new VariableExp(var);
        
        env.addScope();
        env.addElement(UndeclaredVariable.getInstance(), false);
        invokeOut(tokenName, expectedResult);
        env.removeScope();
    }
    
    @Test
    @Ignore
    public void testVisit_UmlEnumLiteral() {
        EnumLiteralExp expectedResult = new EnumLiteralExp();
        List<String> pathName = new ArrayList<String>(2);
        pathName.add("DaysOfWeek");
        pathName.add("SATURDAY");
        assert env.lookupPathName(pathName) != null;
        assert env.lookupPathName(pathName) instanceof UmlEnumLiteral;
        expectedResult.setEnumerationLiteral((UmlEnumLiteral) env.lookupPathName(pathName));
        
        //TODO: Complete that test and implement enum literal productions!!!
    }

    @Test
    public void testOut_AssociationEnd() {
        AssociationEndCallExp expectedResult = new AssociationEndCallExp();
        assert env.lookupImplicitAssociationEnd("employments") != null;
        expectedResult.setReferredAssociationEnd(env.lookupImplicitAssociationEnd("employments"));
        expectedResult.setSource(new VariableExp(self));
        AssociationEndCallExp result = (AssociationEndCallExp) invokeOut("employments", expectedResult);
        
        assert result.getSource() instanceof VariableExp;
        assert ((VariableExp) result.getSource()).getReferredVariable().equals(self);
        
    }

    @Test
    public void testVisit_Attribute() {
        AttributeCallExp expectedResult = new AttributeCallExp();
        assert env.lookupImplicitAttribute("name") != null;
        expectedResult.setReferredAttribute(env.lookupImplicitAttribute("name"));
        expectedResult.setSource(new VariableExp(self));
        AttributeCallExp result = (AttributeCallExp) invokeOut("name", expectedResult);
        
        assert result.getSource() instanceof VariableExp;
        assert ((VariableExp) result.getSource()).getReferredVariable().equals(self);
        
    }

    @Test
    public void testVisit_Variable() {
        String tokenName = "self";
        
        Variable var = (Variable) env.lookup(tokenName);
        VariableExp expectedResult = new VariableExp(var);
        
        invokeOut(tokenName, expectedResult);
    }
    
    private OclExpression invokeOut(String tokenName, Object expectedResult) {
        ANameOclExpressionCS node = new ANameOclExpressionCS();
        List<PSimpleNameCS> restNames = Collections.emptyList();
        node.setName(new APathNameNameCS(new APathNameCS(new ASimpleSimpleNameCS(new TIdentifier(tokenName)), restNames)));        
        
        resultMap.put(node.getName(), Arrays.asList(new String[] {tokenName}));
        
        NameExpCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, expectedResult);
        
        checkMaps();
        
        OclExpression result = (OclExpression) resultMap.get(node);
        
        clear();
        
        return result;
    }
}
