/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.VariableDeclarationCase;
import org.modelinglab.ocl.core.ast.expressions.BooleanLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.IntegerLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class VariableDeclarationCaseTest extends CaseTestAbstract {

    @Test
    public void testOut_ATypedVariableDeclarationCS() {
        final String varName = "v1";
        
        ATypedAndInitVariableDeclarationCS node = new ATypedAndInitVariableDeclarationCS();
        node.setName(new ASimpleSimpleNameCS(new TIdentifier(varName)));
        node.setValue(new AAndOclExpressionCS());
        node.setType(new ACollectionTypeCS());
        
        Variable var = new Variable(varName, new BooleanLiteralExp(Boolean.TRUE), PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        resultMap.put(node.getName(), varName);
        resultMap.put(node.getValue(), new BooleanLiteralExp(Boolean.TRUE));
        resultMap.put(node.getType(), PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        expectedMap.put(node, var);
        
        VariableDeclarationCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
        
        /*
         * Incorrect case: already declared variable
         */
        clear();
        
        env.addScope();
        env.addElement(var, false);
        
        resultMap.put(node.getName(), varName);
        resultMap.put(node.getValue(), new BooleanLiteralExp(Boolean.TRUE));
        resultMap.put(node.getType(), PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        expectedMap.put(node, var);
        
        try {
            VariableDeclarationCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown when declared variable already exist in that environment";
        } catch (OclParserException ex) {
        }
        finally {
            env.removeScope();
        }

        /*
         * gortiz: OCL 2.2 Specification (VariableDeclarationCS) said that the type should be
         * undefined when the type is not specified. So in the case of a declaration with expression
         * but no type, type will be set to null and the expression could not conform!
         */
//        /*
//         * Incorrect case: var type and var init expression does not conform
//         */
//        clear();
//        
//        resultMap.put(node.getName(), varName);
//        resultMap.put(node.getValue(), new IntegerLiteralExp(2));
//        resultMap.put(node.getType(), PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
//        expectedMap.put(node, var);
//        
//        try {
//            VariableDeclarationCase.getInstance().out(node, resultMap, env);
//            assert false : "An exception should be thrown when variable type and variable init expression type does not conform";
//        } catch (OclParserException ex) {
//        }
    }

    @Test
    public void testOut_AVariableInitVariableDeclarationCS() {
        final String varName = "v1";
        
        AInitVariableDeclarationCS node = new AInitVariableDeclarationCS();
        node.setName(new ASimpleSimpleNameCS(new TIdentifier(varName)));
        node.setValue(new AAndOclExpressionCS());
        
        Variable var = new Variable(varName, new BooleanLiteralExp(Boolean.TRUE), VoidType.getInstance());
        
        resultMap.put(node.getName(), varName);
        resultMap.put(node.getValue(), new BooleanLiteralExp(Boolean.TRUE));
        expectedMap.put(node, var);
        
        VariableDeclarationCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
        
        /*
         * Incorrect case
         */
        clear();
        
        env.addScope();
        env.addElement(var, false);
        
        resultMap.put(node.getName(), varName);
        resultMap.put(node.getValue(), new BooleanLiteralExp(Boolean.TRUE));
        expectedMap.put(node, var);
        
        try {
            VariableDeclarationCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown when declared variable already exist in that environment";
        } catch (OclParserException ex) {
        }
        finally {
            env.removeScope();
        }
    }

    @Test
    public void testOut_AVariableTypedVariableDeclarationCS() {
        final String varName = "v1";
        
        ATypedVariableDeclarationCS node = new ATypedVariableDeclarationCS();
        node.setName(new ASimpleSimpleNameCS(new TIdentifier(varName)));
        node.setType(new ACollectionTypeCS());
        
        Variable var = new Variable(varName, null, PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        resultMap.put(node.getName(), varName);
        resultMap.put(node.getType(), PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        expectedMap.put(node, var);
        
        VariableDeclarationCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
        
        /*
         * Incorrect case
         */
        clear();
        
        env.addScope();
        env.addElement(var, false);
        
        resultMap.put(node.getName(), varName);
        resultMap.put(node.getType(), PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        expectedMap.put(node, var);
        
        try {
            VariableDeclarationCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown when declared variable already exist in that environment";
        } catch (OclParserException ex) {
        }
        finally {
            env.removeScope();
        }
    }

    @Test
    public void testOut_AVariableVariableDeclarationCS() {
        final String varName = "v1";
        
        ANoTypedNoInitVariableDeclarationCS node = new ANoTypedNoInitVariableDeclarationCS();
        node.setName(new ASimpleSimpleNameCS(new TIdentifier(varName)));
        
        Variable var = new Variable(varName, null, VoidType.getInstance());
        
        resultMap.put(node.getName(), varName);
        expectedMap.put(node, var);
        
        VariableDeclarationCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
        
        /*
         * Incorrect case
         */
        clear();
        
        env.addScope();
        env.addElement(var, false);
        
        resultMap.put(node.getName(), varName);
        expectedMap.put(node, var);
        
        try {
            VariableDeclarationCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown when declared variable already exist in that environment";
        } catch (OclParserException ex) {
        }
        finally {
            env.removeScope();
        }
    }
}
