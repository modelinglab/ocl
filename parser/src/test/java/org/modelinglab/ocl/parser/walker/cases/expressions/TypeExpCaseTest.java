/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.TypeExpCase;
import org.modelinglab.ocl.core.ast.expressions.TypeExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TypeExpCaseTest extends CaseTestAbstract {
    
    public TypeExpCaseTest() {
    }

    @Test
    public void testOut() {
        ATypeOclExpressionCS node = 
                new ATypeOclExpressionCS(
                    new APathTypeCS(
                        new ASimpleNameNameCS(
                            new ASimpleSimpleNameCS(
                                new TIdentifier("Integer")))));
        
        
        resultMap.put(node.getType(), PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        
        TypeExpCase.getInstance().out(node, resultMap, env);
        
        Classifier referredType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER);
        TypeExp result = referredType.getClassifierType().createExpression();
        expectedMap.put(node, result);
        
        checkMaps();
    }

}
