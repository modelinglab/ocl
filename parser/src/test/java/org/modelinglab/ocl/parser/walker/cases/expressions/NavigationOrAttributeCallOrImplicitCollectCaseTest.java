/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.NavigationOrAttributeCallOrImplicitCollectCase;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.AttributeCallExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.expressions.VariableExp;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.sablecc.node.*;
import java.util.Arrays;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class NavigationOrAttributeCallOrImplicitCollectCaseTest extends CaseTestAbstract {

    @Test
    public void testOut_attribute() {
        ANavigationOrPropertyCallOrImplicitCollectOclExpressionCS node = new ANavigationOrPropertyCallOrImplicitCollectOclExpressionCS();
        
        UmlClass personType = (UmlClass) env.lookup("Employee");
        Variable var = new Variable("var");
        var.setType(personType);
        VariableExp source = new VariableExp(var);        
        node.setSource(new AAndOclExpressionCS());
        resultMap.put(node.getSource(), source);
        
        String featureName = "name";
        node.setNameCS(new ASimpleNameNameCS(new ASimpleSimpleNameCS(new TIdentifier(featureName))));
        resultMap.put(node.getNameCS(), Arrays.asList(new String[] {featureName}));
        
        AttributeCallExp expectedResult = new AttributeCallExp();
        expectedResult.setSource(source.clone());
        
        Attribute attr = null;
        for(Attribute a : personType.getAllAttributes()) {
            if (a.getName().equals(featureName)) {
                attr = a;
                break;
            }
        }
        assert attr != null;
        expectedResult.setReferredAttribute(attr);
        
        expectedMap.put(node, expectedResult);
        
        NavigationOrAttributeCallOrImplicitCollectCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }
}
