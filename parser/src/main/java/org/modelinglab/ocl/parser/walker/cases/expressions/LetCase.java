/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.LetExp;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.core.exceptions.OclRuntimeException;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.ALetOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.PVariableDeclarationCS;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;

/**
 * TODO: NOT TESTED!
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class LetCase {
    
    private LetCase() {
    }

    public void in(ALetOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) {
        env.addScope();
    }
    
    public void postVariable(ALetOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env, PVariableDeclarationCS varDec) {
        Variable var = (Variable) map.get(varDec);
        assert env.lookup(var.getName()) == null : "The variable name must be unique in the current scope. "
                + "It should be checked in variable declaration production!";
        if (var.getType() instanceof VoidType) {
            throw new OclParserException(node, "A variable declaration inside a let must have a declared type.");
        }
        if (var.getInitExpression() == null) {
            throw new OclParserException(node, "A variable declaration inside a let must have an init value.");
        }
        try {
            env.addElement(var, false);
        }
        catch (OclRuntimeException ex) {
            throw new OclParserException(node, ex);
        }
    }

    public void out(ALetOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) {
        env.removeScope();
        
        LetExp ghostLet = new LetExp(); //a let that will not exist in the result, but is used as root to simplify the transformation
        LetExp lastLet = ghostLet; //lastLet.getIn() will be the first valid let expression (ie, the result)
        LetExp exp;
        
        for (PVariableDeclarationCS var : node.getLetVars()) {
            exp = new LetExp();
            
            exp.setVariable((Variable) map.remove(var));
            lastLet.setIn(exp);
            lastLet = exp;
        }
        lastLet.setIn((OclExpression) map.remove(node.getInExpr()));
        
        map.put(node, ghostLet.getIn());
    }
    
    public static LetCase getInstance() {
        return LetCaseHolder.INSTANCE;
    }
    
    private static class LetCaseHolder {

        private static final LetCase INSTANCE = new LetCase();

        private LetCaseHolder() {
        }
    }
}
