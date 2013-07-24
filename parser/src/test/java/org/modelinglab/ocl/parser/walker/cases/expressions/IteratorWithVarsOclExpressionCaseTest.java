/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.IteratorWithVarsOclExpressionCase;
import org.modelinglab.ocl.core.ast.expressions.BooleanLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.modelinglab.ocl.parser.walker.WalkerUtils;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.walker.pojos.ItVariableList;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IteratorWithVarsOclExpressionCaseTest extends CaseTestAbstract {

    @Test
    public void testPreBody_errors() {
        int initScopeLevel = env.getNumberOfScopes();

        AIteratorWithVarsOclExpressionCS node = new AIteratorWithVarsOclExpressionCS();

        node.setSource(new AAndOclExpressionCS());
        resultMap.put(node.getSource(), new BooleanLiteralExp(Boolean.TRUE));

        node.setVars(new AItsArgOrItVar());

        /*
         * An exception should be thrown when vars are mapped to no ItVariableList
         */
        resultMap.put(node.getVars(), new Integer(123));

        try {
            IteratorWithVarsOclExpressionCase.getInstance().preBody(node, resultMap, env);
            assert false : "An exception should be thrown when vars are mapped to no ItVariableList";
        } catch (OclParserException ex) {
            env.removeScope();
            assert initScopeLevel == env.getNumberOfScopes();
        }

        /*
         * An exception should be thrown when there is a variable that is initialized
         */
        ItVariableList varList = new ItVariableList(1);
        Variable var = new Variable("initializedVar");
        var.setInitExpression(new BooleanLiteralExp(Boolean.TRUE));
        varList.add(var);

        resultMap.put(node.getSource(), new BooleanLiteralExp(Boolean.TRUE));
        resultMap.put(node.getVars(), varList);

        try {
            IteratorWithVarsOclExpressionCase.getInstance().preBody(node, resultMap, env);
            assert false : "An exception should be thrown when there is a variable that is initialized";
        } catch (OclParserException ex) {
            env.removeScope();
            assert initScopeLevel == env.getNumberOfScopes();
        }
    }

    @Test
    public void testProcess() {
        final String correctIteratorName = "forAll";
        final Classifier expectedType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        
        int initScopeLevel = env.getNumberOfScopes();

        /*
         * Source
         */
        AIteratorWithVarsOclExpressionCS node = new AIteratorWithVarsOclExpressionCS();

        node.setSource(new AAndOclExpressionCS());
        OclExpression source = new BooleanLiteralExp(Boolean.TRUE);
        resultMap.put(node.getSource(), source.clone());

        /*
         * Vars
         */
        node.setVars(new AItsArgOrItVar());

        ItVariableList varList = new ItVariableList(4);

        Variable noNameVar = new Variable("");
        varList.add(noNameVar);

        Variable nullTypeVar = new Variable("nullTypeVar");
        nullTypeVar.setType(null);
        varList.add(nullTypeVar);

        Variable undefinedTypeVar = new Variable("undefinedTypeVar");
        undefinedTypeVar.setType(VoidType.getInstance());
        varList.add(undefinedTypeVar);

        Variable normalVariable = new Variable("normalVar");
        normalVariable.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        varList.add(normalVariable);

        resultMap.put(node.getVars(), varList);

        /*
         * Body
         */
        node.setBody(new AAndOclExpressionCS());
        OclExpression body = new BooleanLiteralExp(Boolean.TRUE);
        resultMap.put(node.getBody(), body.clone());

        IteratorWithVarsOclExpressionCase.getInstance().preBody(node, resultMap, env);

        /*
         * Iterator name
         */
        node.setSimpleNameCS(new ASimpleSimpleNameCS(new TIdentifier("invalidIteratorName")));
        resultMap.put(node.getSimpleNameCS(), "invalidIteratorName");

        assert env.lookup("").equals(noNameVar);
        assert noNameVar.getName().isEmpty();

        assert env.lookup(nullTypeVar.getName()).equals(nullTypeVar);
        assert nullTypeVar.getName().equals("nullTypeVar");
        assert nullTypeVar.getType().equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));

        assert env.lookup(undefinedTypeVar.getName()).equals(undefinedTypeVar);
        assert undefinedTypeVar.getName().equals("undefinedTypeVar");
        assert undefinedTypeVar.getType().equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));

        assert env.lookup(normalVariable.getName()).equals(normalVariable);
        assert normalVariable.getType().equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        assert normalVariable.getName().equals("normalVar");

        assert initScopeLevel + 1 == env.getNumberOfScopes();

        /*
         * First we check the error case
         */
        try {
            IteratorWithVarsOclExpressionCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown when iterator name does not correspond with an iterator";
        } catch (OclParserException ex) {
            /*
             * this section restores the correct state and set a new name for a valid iterator
             */
            if (initScopeLevel + 1 != env.getNumberOfScopes()) {
                assert initScopeLevel == env.getNumberOfScopes();
                env.addScope();
            }
            if (!resultMap.containsKey(node.getBody())) {
                resultMap.put(node.getBody(), body.clone());
            }
            if (!resultMap.containsKey(node.getSource())) {
                resultMap.put(node.getSource(), source.clone());
            }
            if (resultMap.containsKey(node.getVars())) {
                resultMap.remove(node.getVars());
            }
            assert !resultMap.containsKey(node.getVars());
            resultMap.put(node.getVars(), varList.clone());
            
            if (resultMap.containsKey(node.getSimpleNameCS())) {
                resultMap.remove(node.getSimpleNameCS());
            }
            resultMap.put(node.getSimpleNameCS(), correctIteratorName);
        }
        IteratorWithVarsOclExpressionCase.getInstance().preBody(node, resultMap, env);
        
        IteratorWithVarsOclExpressionCase.getInstance().out(node, resultMap, env);

        IteratorExp result = (IteratorExp) resultMap.remove(node);
        assert resultMap.isEmpty();

        assert result.getBody().equals(body);
        assert result.getName().equals(correctIteratorName);
        
        assert result.getIterators().contains(noNameVar);
        assert result.getIterators().contains(nullTypeVar);
        assert result.getIterators().contains(undefinedTypeVar);
        assert result.getIterators().contains(normalVariable);
        assert result.getIterators().size() == 4;
        
        source = WalkerUtils.getInstance().implicitCollectionCast(source, env);
        assert result.getSource().equals(source);
        assert result.getType().equals(expectedType);
    }
}
