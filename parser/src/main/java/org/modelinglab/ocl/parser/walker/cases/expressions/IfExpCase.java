/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.IfExp;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.AIfOclExpressionCS;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IfExpCase {
    
    private IfExpCase() {
    }

    public static IfExpCase getInstance() {
        return IfExpCaseHolder.INSTANCE;
    }
    
    public void out(AIfOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        IfExp result = new IfExp();
        
        result.setCondition((OclExpression) map.remove(node.getCondition()));
        result.setThenExpression(((OclExpression) map.remove(node.getThenExpr())));
        result.setElseExpression(((OclExpression) map.remove(node.getElseExpr())));
        
        map.put(node, result);
    }
    
    private static class IfExpCaseHolder {

        private static final IfExpCase INSTANCE = new IfExpCase();

        private IfExpCaseHolder() {
        }
    }
}
