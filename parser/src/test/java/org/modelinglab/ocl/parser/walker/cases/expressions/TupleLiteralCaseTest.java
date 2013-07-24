/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.LiteralCase;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.sablecc.node.AInitVariableDeclarationCS;
import org.modelinglab.ocl.parser.sablecc.node.ANoTypedNoInitVariableDeclarationCS;
import org.modelinglab.ocl.parser.sablecc.node.ATupleLiteralOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.PVariableDeclarationCS;
import java.util.Arrays;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TupleLiteralCaseTest extends CaseTestAbstract {

    @Test
    public void testOut_empty() {
        ATupleLiteralOclExpressionCS node = new ATupleLiteralOclExpressionCS();

        LiteralCase.getInstance().out(node, resultMap, env);

        expectedMap.put(node, new TupleLiteralExp());

        checkMaps();
    }

    @Test
    public void testOut_unamedVar() {
        ATupleLiteralOclExpressionCS node = new ATupleLiteralOclExpressionCS();
        TupleLiteralExp expectedResult = new TupleLiteralExp();

        Variable var = new Variable();
        var.setInitExpression(new BooleanLiteralExp(Boolean.TRUE));
        var.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));

        node.setVars(Arrays.asList(new PVariableDeclarationCS[]{new AInitVariableDeclarationCS()}));
        TupleLiteralPart part = new TupleLiteralPart(var.clone());
        expectedResult.setParts(Arrays.asList(new TupleLiteralPart[]{part}));

        resultMap.put(node.getVars().get(0), var.clone());

        expectedMap.put(node, expectedResult);

        try {
            LiteralCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown when a tuple literal part has no name!";
        } catch (OclParserException ex) {
        }
    }

    @Test
    public void testOut_notInitializedVar() {
        ATupleLiteralOclExpressionCS node = new ATupleLiteralOclExpressionCS();
        TupleLiteralExp expectedResult = new TupleLiteralExp();

        Variable var = new Variable("v1");
        var.setInitExpression(null);
        var.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));

        node.setVars(Arrays.asList(new PVariableDeclarationCS[]{new AInitVariableDeclarationCS()}));
        TupleLiteralPart part = new TupleLiteralPart(var.clone());
        expectedResult.setParts(Arrays.asList(new TupleLiteralPart[]{part}));

        resultMap.put(node.getVars().get(0), var.clone());

        expectedMap.put(node, expectedResult);

        try {
            LiteralCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown when a tuple literal part is not initialized!";
        } catch (OclParserException ex) {
        }
    }

    @Test
    public void testOut_undefinedTypeVar() {
        ATupleLiteralOclExpressionCS node = new ATupleLiteralOclExpressionCS();
        TupleLiteralExp expectedResult = new TupleLiteralExp();

        Variable var = new Variable("v1");
        var.setInitExpression(new NullLiteralExp());
        var.setType(VoidType.getInstance());

        node.setVars(Arrays.asList(new PVariableDeclarationCS[]{new ANoTypedNoInitVariableDeclarationCS()}));
        TupleLiteralPart part = new TupleLiteralPart(var.clone());
        expectedResult.setParts(Arrays.asList(new TupleLiteralPart[]{part}));

        resultMap.put(node.getVars().get(0), var.clone());

        expectedMap.put(node, expectedResult);

        try {
            LiteralCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown when a tuple literal part has an undefined type!";
        } catch (OclParserException ex) {
        }
    }

    @Test
    public void testOut_correct() {
        ATupleLiteralOclExpressionCS node = new ATupleLiteralOclExpressionCS();
        TupleLiteralExp expectedResult = new TupleLiteralExp();

        Variable var = new Variable("v1");
        var.setInitExpression(new BooleanLiteralExp(Boolean.TRUE));
        var.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));

        node.setVars(Arrays.asList(new PVariableDeclarationCS[]{new AInitVariableDeclarationCS()}));
        TupleLiteralPart part = new TupleLiteralPart(var.clone());
        expectedResult.setParts(Arrays.asList(new TupleLiteralPart[]{part}));

        resultMap.put(node.getVars().get(0), var.clone());

        expectedMap.put(node, expectedResult);

        LiteralCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }
}
