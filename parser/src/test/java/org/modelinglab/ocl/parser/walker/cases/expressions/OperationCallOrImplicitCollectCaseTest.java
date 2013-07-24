/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.OperationCallOrImplicitCollectCase;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.exceptions.AmbiguosOperationCall;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.sablecc.node.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.modelinglab.ocl.core.standard.iterators.exceptions.IllegalIteratorException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OperationCallOrImplicitCollectCaseTest extends CaseTestAbstract {
    
    @Test
    public void testOut_operationCall_withoutArgs() throws AmbiguosOperationCall {
        final String opName = "oclIsUndefined";
        AOperationCallOrImplicitCollectOclExpressionCS node = new AOperationCallOrImplicitCollectOclExpressionCS();
        
        node.setSource(new AAndOclExpressionCS());
        OclExpression source = new BooleanLiteralExp(Boolean.TRUE);
        resultMap.put(node.getSource(), source.clone());
        
        node.setNameCS(new ASimpleNameNameCS(new ASimpleSimpleNameCS(new TIdentifier(opName))));
        resultMap.put(node.getNameCS(), Arrays.asList(new String[] {opName}));
        
        /*
         * no args
         */
        List<Classifier> argsTypes = Collections.emptyList();
        List<OclExpression> args = Collections.emptyList();
        
        OperationCallExp expectedResult = new OperationCallExp();
        expectedResult.setSource(source.clone());
        expectedResult.setReferredOperation(env.lookupOperation(source, opName, argsTypes));
        expectedResult.setArguments(args);

        expectedMap.put(node, expectedResult);
        
        OperationCallOrImplicitCollectCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }
    
    @Test
    public void testOut_operationCall_withArgs() throws AmbiguosOperationCall {
        final String opName = "oclIsTypeOf";
        AOperationCallOrImplicitCollectOclExpressionCS node = new AOperationCallOrImplicitCollectOclExpressionCS();
        
        node.setSource(new AAndOclExpressionCS());
        OclExpression source = new BooleanLiteralExp(Boolean.TRUE);
        resultMap.put(node.getSource(), source.clone());
        
        node.setNameCS(new ASimpleNameNameCS(new ASimpleSimpleNameCS(new TIdentifier(opName))));
        resultMap.put(node.getNameCS(), Arrays.asList(new String[] {opName}));
        
        /*
         * Args
         */
        List<Classifier> argsTypes = new ArrayList<Classifier>(1);
        List<OclExpression> args = new ArrayList<OclExpression>(1);
        
        Classifier referredType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        TypeExp typeExp = referredType.getClassifierType().createExpression();
        args.add(typeExp);
        argsTypes.add(typeExp.getType());
        
        POclExpressionCS pArg = new AAndOclExpressionCS();
        node.setArgs(Arrays.asList(new POclExpressionCS[] {pArg}));
        resultMap.put(pArg, typeExp);
        
        OperationCallExp expectedResult = new OperationCallExp();
        expectedResult.setSource(source.clone());
        expectedResult.setReferredOperation(env.lookupOperation(source, opName, argsTypes));
        expectedResult.setArguments(Arrays.asList(new OclExpression[] {args.get(0).clone()}));

        expectedMap.put(node, expectedResult);
        
        OperationCallOrImplicitCollectCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }
    
    @Test
    public void testOut_collectOpCall_withArgs() throws AmbiguosOperationCall, IllegalIteratorException {
        final String opName = "oclIsTypeOf";
        AOperationCallOrImplicitCollectOclExpressionCS node = new AOperationCallOrImplicitCollectOclExpressionCS();
        
        node.setSource(new AAndOclExpressionCS());
        CollectionLiteralExp source = new CollectionLiteralExp();
        source.setType(new BagType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN)));
        resultMap.put(node.getSource(), source.clone());
        
        node.setNameCS(new ASimpleNameNameCS(new ASimpleSimpleNameCS(new TIdentifier(opName))));
        resultMap.put(node.getNameCS(), Arrays.asList(new String[] {opName}));
        
        /*
         * Args
         */
        List<Classifier> argsTypes = new ArrayList<Classifier>(1);
        List<OclExpression> args = new ArrayList<OclExpression>(1);
        
        Classifier referredType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        TypeExp typeExp = referredType.getClassifierType().createExpression();
        args.add(typeExp);
        argsTypes.add(typeExp.getType());
        
        POclExpressionCS pArg = new AAndOclExpressionCS();
        node.setArgs(Arrays.asList(new POclExpressionCS[] {pArg}));
        resultMap.put(pArg, typeExp);
        
        CollectionType sourceType = source.getType();
        
        Variable itVar = new Variable();
        assert sourceType.getElementType() != null;
        itVar.setType(sourceType.getElementType());
        
        Variable expectedItVar = itVar.clone();
        expectedItVar.setName("");
        
        VariableExp varExp = new VariableExp();
        varExp.setReferredVariable(expectedItVar.clone());
        
        OperationCallExp body = new OperationCallExp();
        body.setSource(varExp);
        body.setReferredOperation(env.lookupOperation(sourceType.getElementType(), opName, argsTypes));
        body.setArguments(Arrays.asList(new OclExpression[] {args.get(0).clone()}));
        
        IteratorExp result = env.lookupIterator(sourceType, "collect", itVar.getType());
        result.setBody(body);
        result.setSource(source.clone());
        
        List<Variable> itVars = new ArrayList<Variable>(1);
        itVars.add(expectedItVar);
        result.setIterators(itVars);        

        expectedMap.put(node, result);
        
        OperationCallOrImplicitCollectCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }
    
    @Test
    public void testOut_collectOpCall_withoutArgs() throws AmbiguosOperationCall, IllegalIteratorException {
        final String opName = "oclIsUndefined";
        AOperationCallOrImplicitCollectOclExpressionCS node = new AOperationCallOrImplicitCollectOclExpressionCS();
        
        node.setSource(new AAndOclExpressionCS());
        CollectionLiteralExp source = new CollectionLiteralExp();
        source.setType(new BagType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN)));
        resultMap.put(node.getSource(), source.clone());
        
        node.setNameCS(new ASimpleNameNameCS(new ASimpleSimpleNameCS(new TIdentifier(opName))));
        resultMap.put(node.getNameCS(), Arrays.asList(new String[] {opName}));
        
        /*
         * no args
         */
        List<Classifier> argsTypes = Collections.emptyList();
        List<OclExpression> args = Collections.emptyList();
        
        CollectionType sourceType = source.getType();
        
        Variable itVar = new Variable();
        assert sourceType.getElementType() != null;
        itVar.setType(sourceType.getElementType());
        
        Variable expectedItVar = itVar.clone();
        expectedItVar.setName("");
        
        VariableExp varExp = new VariableExp();
        varExp.setReferredVariable(expectedItVar);
        
        OperationCallExp body = new OperationCallExp();
        body.setSource(varExp);
        body.setReferredOperation(env.lookupOperation(sourceType.getElementType(), opName, argsTypes));
        body.setArguments(args);
        
        IteratorExp result = env.lookupIterator(sourceType, "collect", itVar.getType());
        result.setBody(body);
        result.setSource(source.clone());       

        List<Variable> itVars = new ArrayList<Variable>(1);
        itVars.add(expectedItVar);
        result.setIterators(itVars);
        
        expectedMap.put(node, result);
        
        OperationCallOrImplicitCollectCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }
}
