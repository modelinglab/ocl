/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.IterateOclExpressionCase;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.sablecc.node.AAndOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.AIterateOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.ANoTypedNoInitVariableDeclarationCS;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IterateOclExpressionCaseTest extends CaseTestAbstract {
    
    /**
     * Tests preBody, postBody and out methods
     * @see IterateOclExpressionCase#postBody
     * @see IterateOclExpressionCase#preBody
     * @see IterateOclExpressionCase#out
     */
    @Test
    public void testProcess() {
        
        int initialScopeLevel = env.getNumberOfScopes();
        
        AIterateOclExpressionCS node = new AIterateOclExpressionCS();
        
        node.setSource(new AAndOclExpressionCS());
        resultMap.put(node.getSource(), new BooleanLiteralExp(Boolean.FALSE));
        
        //implicit iterator variable
        node.setVar(null);
        
        node.setAcc(new ANoTypedNoInitVariableDeclarationCS());
        Variable resultVar = new Variable();
        resultVar.setName("result");
        resultVar.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        resultVar.setInitExpression(new BooleanLiteralExp(Boolean.FALSE));
        resultMap.put(node.getAcc(), resultVar.clone());
        
        IterateOclExpressionCase.getInstance().preBody(node, resultMap, env);
        
        assert env.lookup("") != null;
        assert env.lookup("") instanceof Variable;
        
        assert env.lookup("result") != null;
        assert env.lookup("result").equals(resultVar);
        
        assert resultMap.containsKey(node);
        assert resultMap.get(node) instanceof IterateExp;
        assert ((IterateExp) resultMap.get(node)).getResult().equals(resultVar);
        assert ((IterateExp) resultMap.get(node)).getIterators().size() == 1;
        
        node.setBody(new AAndOclExpressionCS());
        resultMap.put(node.getBody(), new BooleanLiteralExp(Boolean.FALSE));
        
        IterateOclExpressionCase.getInstance().postBody(node, resultMap, env);
        assert env.lookup("") == null;
        assert env.lookup("result") == null;
        
        IterateExp expectedResult = new IterateExp();
        expectedResult.setBody((OclExpression) resultMap.get(node.getBody()));
        expectedResult.setResult(resultVar.clone());
        
        IterateOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedResult.addIterator(((IterateExp) resultMap.get(node)).getIterators().iterator().next().clone());
        
        expectedMap.put(node, expectedResult);
        
        assert initialScopeLevel == env.getNumberOfScopes();
    }
    
    @Test
    public void testGetIterator_empty() {
        AIterateOclExpressionCS node = new AIterateOclExpressionCS();
        CollectionType sourceType = new BagType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        Variable iteratorVar = IterateOclExpressionCase.getInstance().getIterator(node, sourceType, resultMap);
        
        assert iteratorVar != null;
        assert iteratorVar.getName().isEmpty();
        assert iteratorVar.getType().equals(sourceType.getElementType());
    }
    
    @Test
    public void testGetIterator_nullType() {
        AIterateOclExpressionCS node = new AIterateOclExpressionCS();
        node.setVar(new ANoTypedNoInitVariableDeclarationCS());
        
        Variable iteratorVar = new Variable();
        iteratorVar.setName("it");
        iteratorVar.setType(null);
        
        resultMap.put(node.getVar(), iteratorVar);
        
        CollectionType sourceType = new BagType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        IterateOclExpressionCase.getInstance().getIterator(node, sourceType, resultMap);
        
        assert iteratorVar.getName().equals("it"); //name does not change
        assert iteratorVar.getType().equals(sourceType.getElementType());
    }
    
    @Test
    public void testGetIterator_undefinedType() {
        AIterateOclExpressionCS node = new AIterateOclExpressionCS();
        node.setVar(new ANoTypedNoInitVariableDeclarationCS());
        
        Variable iteratorVar = new Variable();
        iteratorVar.setName("it");
        iteratorVar.setType(VoidType.getInstance());
        assert iteratorVar.getType().oclIsUndefined();
        
        resultMap.put(node.getVar(), iteratorVar);
        
        CollectionType sourceType = new BagType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        IterateOclExpressionCase.getInstance().getIterator(node, sourceType, resultMap);
        
        assert iteratorVar.getName().equals("it"); //name does not change
        assert iteratorVar.getType().equals(sourceType.getElementType());
    }
    
    @Test
    public void testGetIterator_initExp() {
        AIterateOclExpressionCS node = new AIterateOclExpressionCS();
        node.setVar(new ANoTypedNoInitVariableDeclarationCS());
        
        Variable iteratorVar = new Variable();
        iteratorVar.setName("it");
        iteratorVar.setInitExpression(new NullLiteralExp());
        
        resultMap.put(node.getVar(), iteratorVar);
        
        CollectionType sourceType = new BagType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        try {
            IterateOclExpressionCase.getInstance().getIterator(node, sourceType, resultMap);
            assert false : "An exception should be thrown when the iterator variable has an initial value.";
        } catch (OclParserException ex) {
        }
    }

    @Test
    public void testGetResultVar_nullType() {
        AIterateOclExpressionCS node = new AIterateOclExpressionCS();
        node.setAcc(new ANoTypedNoInitVariableDeclarationCS());
        
        Variable resultVar = new Variable();
        
        resultMap.put(node.getAcc(), resultVar);
        
        try {
            IterateOclExpressionCase.getInstance().getResultVar(node, resultMap);
            assert false : "An exception should be thrown when result variable has no defined type.";
        } catch (OclParserException ex) {
        }
    }

    @Test
    public void testGetResultVar_undefinedType() {
        AIterateOclExpressionCS node = new AIterateOclExpressionCS();
        node.setAcc(new ANoTypedNoInitVariableDeclarationCS());
        
        Variable resultVar = new Variable();
        resultVar.setType(VoidType.getInstance());
        
        resultMap.put(node.getAcc(), resultVar);
        
        try {
            IterateOclExpressionCase.getInstance().getResultVar(node, resultMap);
            assert false : "An exception should be thrown when result variable has no defined type.";
        } catch (OclParserException ex) {
        }
    }

    @Test
    public void testGetResultVar_withoutInitExp() {
        AIterateOclExpressionCS node = new AIterateOclExpressionCS();
        node.setAcc(new ANoTypedNoInitVariableDeclarationCS());
        
        Variable resultVar = new Variable();
        resultVar.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        resultMap.put(node.getAcc(), resultVar);
        
        try {
            IterateOclExpressionCase.getInstance().getResultVar(node, resultMap);
            assert false : "An exception should be thrown when result variable has no initial value.";
        } catch (OclParserException ex) {
        }
    }

    @Test
    public void testGetResultVar_correct() {
        AIterateOclExpressionCS node = new AIterateOclExpressionCS();
        node.setAcc(new ANoTypedNoInitVariableDeclarationCS());
        
        Variable resultVar = new Variable();
        resultVar.setName("result");
        resultVar.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        resultVar.setInitExpression(new BooleanLiteralExp(Boolean.FALSE));
        
        resultMap.put(node.getAcc(), resultVar);
        
        IterateOclExpressionCase.getInstance().getResultVar(node, resultMap);
        
        assert resultVar.getName().equals("result");
        assert resultVar.getType().equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        assert resultVar.getInitExpression().equals(new BooleanLiteralExp(Boolean.FALSE));
    }
}
