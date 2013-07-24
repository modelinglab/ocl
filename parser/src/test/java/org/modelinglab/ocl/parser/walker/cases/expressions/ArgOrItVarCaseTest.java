/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.ArgOrItVarCase;
import org.modelinglab.ocl.core.ast.expressions.InvalidLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.expressions.VariableExp;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.walker.pojos.ItVariableList;
import org.modelinglab.ocl.parser.walker.pojos.UndeclaredVariable;
import java.util.ArrayList;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ArgOrItVarCaseTest extends CaseTestAbstract {

    @Test
    public void testOut_AItsArgOrItVar() {
        AItsArgOrItVar node = new AItsArgOrItVar();
        
        ArrayList<PVariableDeclarationCS> varDecs3 = new ArrayList<PVariableDeclarationCS>(3);
        ArrayList<PVariableDeclarationCS> varDecs2 = new ArrayList<PVariableDeclarationCS>(3);
        ArrayList<Variable> vars = new ArrayList<Variable>(3);
        
        /*
         * @gortiz sablecc has a bug. If you set a subnodelist element twice, result the element is
         * removed!
         * We need have two similar elements to avoid this behavour
         */
        for (int i = 0; i < 3; i++) {
            PVariableDeclarationCS varProd = new ANoTypedNoInitVariableDeclarationCS(new ASimpleSimpleNameCS(new TIdentifier("var"+i)));
            varDecs3.add(varProd);
            
            Variable var = new Variable();
            var.setType(VoidType.getInstance());
            var.setName("var"+i);
           
            vars.add(var);
            resultMap.put(varProd, var);
        }
        for (int i = 0; i < 2; i++) {
            PVariableDeclarationCS varProd = new ANoTypedNoInitVariableDeclarationCS(new ASimpleSimpleNameCS(new TIdentifier("var"+i)));
            varDecs2.add(varProd);            
        }
        
        node.setVars(varDecs3);
        
        try {
            ArgOrItVarCase.getInstance().out(node, resultMap, env);
            assert false : "If there are more than 2 iterator variables, an exception should be"
                    + "thrown.";
        }
        catch (OclParserException ex) {
        }
        
        vars.remove(2);
        node.setVars(varDecs2);
        clear();
        for (int i = 0; i < 2; i++) {
            resultMap.put(varDecs2.get(i), vars.get(i));
        }
        
        assert node.getVars().size() == 2;
        
        int numScopesPre = env.getNumberOfScopes();
        
        ArgOrItVarCase.getInstance().out(node, resultMap, env);
        
        assert numScopesPre == env.getNumberOfScopes();
        
        ItVariableList result = new ItVariableList(2);
        result.addAll(vars);
        expectedMap.put(node, result);
        
        checkMaps();
    }

    @Test
    public void testIn_AUntypedItAndOtherItArgOrItVar() {
        int numScopesPre = env.getNumberOfScopes();
        assert env.lookup(UndeclaredVariable.getInstance().getName()) == null;

        AUntypedItAndOtherItArgOrItVar node = new AUntypedItAndOtherItArgOrItVar();
        ArgOrItVarCase.getInstance().in(node, resultMap, env);

        assert env.lookup(UndeclaredVariable.getInstance().getName()).equals(UndeclaredVariable.getInstance());
        assert numScopesPre == env.getNumberOfScopes() - 1;
        env.removeScope();
    }

    @Test
    public void testOut_AUntypedItAndOtherItArgOrItVar() {
        POclExpressionCS firstNode = new AAndOclExpressionCS();
        PVariableDeclarationCS secondNode = new ANoTypedNoInitVariableDeclarationCS(new ASimpleSimpleNameCS(new TIdentifier("itVar2")));

        AUntypedItAndOtherItArgOrItVar node = new AUntypedItAndOtherItArgOrItVar();

        node.setUndeclaredVariableExp(firstNode);
        node.setVar2(secondNode);

        Object notVariableExp = new InvalidLiteralExp();
        
        VariableExp alreadyDefinedVariableExp = new VariableExp();
        Variable alreadyDefinedVar = new Variable();
        alreadyDefinedVar.setName("definedVariable");
        alreadyDefinedVariableExp.setReferredVariable(alreadyDefinedVar);
                
        VariableExp correctVar1 = new VariableExp();
        Variable var1 = new Variable();
        var1.setName("itVar1");
        var1.setType(VoidType.getInstance());
        correctVar1.setReferredVariable(var1);

        Variable correctVar2 = new Variable();
        correctVar2.setName("itVar2");
        correctVar2.setType(VoidType.getInstance());


        /*
         * If firstNode is mapped to a not variable expression, an exception should be thrown
         */
        int numScopesPre = env.getNumberOfScopes();
        try {
            resultMap.put(firstNode, notVariableExp);
            resultMap.put(secondNode, correctVar2);

            ArgOrItVarCase.getInstance().in(node, resultMap, env);
            ArgOrItVarCase.getInstance().out(node, resultMap, env);
            assert false : "If firstNode is mapped to a not variable expression, an exception "
                    + "should be throw";
        } catch (OclParserException ex) {
            assert env.getNumberOfScopes() >= numScopesPre;
            while (env.getNumberOfScopes() > numScopesPre) {
                env.removeScope();
            }
        }
        clear();
        
        /*
         * If firstNode is mapped to a variable expression but the variable is already defined, an exception should be thrown
         */
        try {
            resultMap.put(firstNode, alreadyDefinedVariableExp);
            resultMap.put(secondNode, correctVar2);

            ArgOrItVarCase.getInstance().in(node, resultMap, env);
            env.addElement(alreadyDefinedVar, false);
            ArgOrItVarCase.getInstance().out(node, resultMap, env);
            assert false : "If firstNode is mapped to a variable expression but the variable is "
                    + "already defined, an exception should be thrown";
        } catch (OclParserException ex) {
            assert env.getNumberOfScopes() >= numScopesPre;
            while (env.getNumberOfScopes() > numScopesPre) {
                env.removeScope();
            }
        }
        clear();
        
        numScopesPre = env.getNumberOfScopes();

        resultMap.put(firstNode, correctVar1);
        resultMap.put(secondNode, correctVar2);

        ArgOrItVarCase.getInstance().in(node, resultMap, env);
        ArgOrItVarCase.getInstance().out(node, resultMap, env);
        
        ItVariableList result = new ItVariableList(2);
        result.add(var1);
        result.add(correctVar2);
        
        expectedMap.put(node, result);
        checkMaps();
        
        assert numScopesPre == env.getNumberOfScopes();
    }
}
