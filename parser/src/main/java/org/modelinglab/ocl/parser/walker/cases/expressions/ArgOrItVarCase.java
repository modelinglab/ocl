/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.expressions.VariableExp;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.AItsArgOrItVar;
import org.modelinglab.ocl.parser.sablecc.node.AUntypedItAndOtherItArgOrItVar;
import org.modelinglab.ocl.parser.sablecc.node.PVariableDeclarationCS;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import org.modelinglab.ocl.parser.walker.pojos.ItVariableList;
import org.modelinglab.ocl.parser.walker.pojos.UndeclaredVariable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ArgOrItVarCase {

    private ArgOrItVarCase() {
    }

    public static ArgOrItVarCase getInstance() {
        return ArgOrItVarCaseHolder.INSTANCE;
    }

    public void out(AItsArgOrItVar node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        ItVariableList result = new ItVariableList(node.getVars().size());

        if (node.getVars().size() > 2) {
            throw new OclParserException(node, "Iterators can have at most two variables!");
        }
        
        for (PVariableDeclarationCS varDec : node.getVars()) {
            result.add((Variable) map.remove(varDec));
        }

        map.put(node, result);
    }
    
    public void in(AUntypedItAndOtherItArgOrItVar node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        env.addScope();
        env.addElement(UndeclaredVariable.getInstance(), false);
    }

    public void out(AUntypedItAndOtherItArgOrItVar node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        ItVariableList result = new ItVariableList(2);

        OclExpression exp = (OclExpression) map.remove(node.getUndeclaredVariableExp());
        if (!(exp instanceof VariableExp)) {
            throw new OclParserException(node, exp + ", the first iterator variable, is not a variable!");
        }
        VariableExp varExp = (VariableExp) exp;
        if (env.lookup(varExp.getReferredVariable().getName()) != null) { //is an already declared variable
            throw new OclParserException(node, exp +", the first iterator variable, is a variable but is not free!");
        }
        Variable var = new Variable();
        var.setInitExpression(null);
        var.setType(VoidType.getInstance());
        var.setName(((VariableExp) exp).getReferredVariable().getName());
        result.add(var);
        
        result.add((Variable) map.remove(node.getVar2()));

        map.put(node, result);
        
        env.removeScope();
    }

    private static class ArgOrItVarCaseHolder {

        private static final ArgOrItVarCase INSTANCE = new ArgOrItVarCase();

        private ArgOrItVarCaseHolder() {
        }
    }
}
