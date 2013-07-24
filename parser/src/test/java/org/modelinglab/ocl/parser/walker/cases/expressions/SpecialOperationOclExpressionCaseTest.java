/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.exceptions.AmbiguosOperationCall;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.sablecc.node.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class SpecialOperationOclExpressionCaseTest extends CaseTestAbstract {
    
    IntegerLiteralExp intExp = new IntegerLiteralExp(23l);
    RealLiteralExp realExp = new RealLiteralExp(23d);
    BooleanLiteralExp boolExp = new BooleanLiteralExp(Boolean.TRUE);
    StringLiteralExp stringExp = new StringLiteralExp("lala");
    
    private OperationCallExp createInfixOperationCallExp(String opName, OclExpression source, OclExpression arg2) {
        OperationCallExp result = new OperationCallExp();
        
        result.setSource(source);
        result.addArgument(arg2);
        
        List<Classifier> argTypes = Arrays.asList(new Classifier[]{arg2.getType()});
        
        try {
        result.setReferredOperation(env.lookupOperation(
                result.getSource(),
                opName,
                argTypes));
        }
        catch (AmbiguosOperationCall ex) {
            throw new AssertionError(ex);
        }
        return result;
    }
    
    private OperationCallExp createUnaryOperationCallExp(String opName, OclExpression source) {
        OperationCallExp result = new OperationCallExp();

        result.setSource(source);

        List<Classifier> argTypes = Collections.emptyList();

        try {
            result.setReferredOperation(env.lookupOperation(
                result.getSource(),
                opName,
                argTypes));
        }
        catch (AmbiguosOperationCall ex) {
            throw new AssertionError(ex);
        }
        return result;        
    }
    
    @Test
    public void testOut_AAndOclExpressionCS() {
        AAndOclExpressionCS node = new AAndOclExpressionCS();
        
        OclExpression source = boolExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = boolExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("and", source, arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_ADivOclExpressionCS() {
        ADivOclExpressionCS node = new ADivOclExpressionCS();
        
        OclExpression source = realExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = intExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("/", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_ALessOclExpressionCS() {
        ALessOclExpressionCS node = new ALessOclExpressionCS();
        
        OclExpression source = intExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = intExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("<", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_ALessOrEqualOclExpressionCS() {
        ALessOrEqualOclExpressionCS node = new ALessOrEqualOclExpressionCS();
        
        OclExpression source = intExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = realExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("<=", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_AMinusOclExpressionCS() {
        AMinusOclExpressionCS node = new AMinusOclExpressionCS();
        
        OclExpression source = realExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = intExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("-", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_AMultOclExpressionCS() {
        AMultOclExpressionCS node = new AMultOclExpressionCS();
        
        OclExpression source = intExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = realExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("*", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_APlusOclExpressionCS_normal() {
        APlusOclExpressionCS node = new APlusOclExpressionCS();
        
        OclExpression source = intExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = realExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("+", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_APlusOclExpressionCS_concat() {
        APlusOclExpressionCS node = new APlusOclExpressionCS();
        
        OclExpression source = stringExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = stringExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("concat", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    
    @Test
    public void testOut_AUnaryMinusOclExpressionCS() {
        AUnaryMinusOclExpressionCS node = new AUnaryMinusOclExpressionCS();
        
        OclExpression source = intExp;
        node.setOclExpressionCS(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS(), source.clone());
                
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createUnaryOperationCallExp("-", source.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_ANotOclExpressionCS() {
        ANotOclExpressionCS node = new ANotOclExpressionCS();
        
        OclExpression source = boolExp;
        node.setOclExpressionCS(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS(), source.clone());
                
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createUnaryOperationCallExp("not", source.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_AMoreOclExpressionCS() {
        AMoreOclExpressionCS node = new AMoreOclExpressionCS();
        
        OclExpression source = intExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = realExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp(">", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_AMoreOrEqualOclExpressionCS() {
        AMoreOrEqualOclExpressionCS node = new AMoreOrEqualOclExpressionCS();
        
        OclExpression source = intExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = intExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp(">=", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_ANotEqualOclExpressionCS() {
        ANotEqualOclExpressionCS node = new ANotEqualOclExpressionCS();
        
        OclExpression source = intExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = intExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("<>", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_AEqualOclExpressionCS() {
        AEqualOclExpressionCS node = new AEqualOclExpressionCS();
        
        OclExpression source = intExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = intExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("=", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_AOrOclExpressionCS() {
        AOrOclExpressionCS node = new AOrOclExpressionCS();
        
        OclExpression source = boolExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = boolExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("or", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_AXorOclExpressionCS() {
        AXorOclExpressionCS node = new AXorOclExpressionCS();
        
        OclExpression source = boolExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = boolExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("xor", source.clone(), arg.clone()));
        
        checkMaps();
    }
    
    @Test
    public void testOut_AImpliesOclExpressionCS() {
        AImpliesOclExpressionCS node = new AImpliesOclExpressionCS();
        
        OclExpression source = boolExp;
        node.setOclExpressionCS1(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS1(), source.clone());
        
        OclExpression arg = boolExp;
        node.setOclExpressionCS2(new AAndOclExpressionCS());
        resultMap.put(node.getOclExpressionCS2(), arg.clone());
        
        SpecialOperationOclExpressionCase.getInstance().out(node, resultMap, env);
        
        expectedMap.put(node, createInfixOperationCallExp("implies", source.clone(), arg.clone()));
        
        checkMaps();
    }
}
