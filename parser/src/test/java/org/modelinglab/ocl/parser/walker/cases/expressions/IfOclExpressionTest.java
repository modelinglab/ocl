/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.expressions.BooleanLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.IfExp;
import org.modelinglab.ocl.core.ast.expressions.IntegerLiteralExp;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.sablecc.node.AAndOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.ADivOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.AIfOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.AImpliesOclExpressionCS;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IfOclExpressionTest extends CaseTestAbstract {

    @Test
    public void testOut() {
        IfExp expectedResult = new IfExp();

        expectedResult.setCondition(new BooleanLiteralExp(Boolean.TRUE));
        expectedResult.setThenExpression(new IntegerLiteralExp(2l));
        expectedResult.setElseExpression(new IntegerLiteralExp(3l));

        AIfOclExpressionCS node = new AIfOclExpressionCS();
        node.setCondition(new AAndOclExpressionCS());
        node.setThenExpr(new ADivOclExpressionCS());
        node.setElseExpr(new AImpliesOclExpressionCS());

        resultMap.put(node.getCondition(), expectedResult.getCondition().clone());
        resultMap.put(node.getThenExpr(), expectedResult.getThenExpression().clone());
        resultMap.put(node.getElseExpr(), expectedResult.getElseExpression().clone());

        expectedMap.put(node, expectedResult);
        IfExpCase.getInstance().out(node, resultMap, env);
        checkMaps();
    }
}
