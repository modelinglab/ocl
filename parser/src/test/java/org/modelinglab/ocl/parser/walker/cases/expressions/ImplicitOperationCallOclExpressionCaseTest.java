/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.ImplicitSourceToOclExpression;
import org.modelinglab.ocl.parser.walker.cases.expressions.ImplicitOperationCallOclExpressionCase;
import org.modelinglab.ocl.core.ast.expressions.BooleanLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.exceptions.AmbiguosOperationCall;
import org.modelinglab.ocl.parser.sablecc.node.AAndOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.AImplicitOperationCallOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.ASimpleNameNameCS;
import org.modelinglab.ocl.parser.sablecc.node.ASimpleSimpleNameCS;
import org.modelinglab.ocl.parser.sablecc.node.POclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.TIdentifier;
import org.modelinglab.ocl.parser.walker.OclInvalidOperationException;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ImplicitOperationCallOclExpressionCaseTest extends CaseTestAbstract {
    
    @Before
    public void addEmployee() {
        addSelf("Employee");
    }

    @After
    public void removeEmployee() {
        removeSelf();
    }
    
    @Test
    public void testOut_noOp() {
        AImplicitOperationCallOclExpressionCS node = new AImplicitOperationCallOclExpressionCS();
        node.setNameCS(new ASimpleNameNameCS(new ASimpleSimpleNameCS(new TIdentifier("lala"))));
        
        resultMap.put(node.getNameCS(), Arrays.asList(new String[] {"lala"}));
        
        try {
            ImplicitOperationCallOclExpressionCase.getInstance().out(node, resultMap, env);
            assert false : "There is no implicit operation with that name so an exception should have been thrown";
        } catch (OclInvalidOperationException ex) {
        }
    }
    
    @Test
    public void testOut_withoutArgs() throws AmbiguosOperationCall {
        String opName = "opWithoutArgs";
        
        AImplicitOperationCallOclExpressionCS node = new AImplicitOperationCallOclExpressionCS();
        node.setNameCS(new ASimpleNameNameCS(new ASimpleSimpleNameCS(new TIdentifier(opName))));
        
        resultMap.put(node.getNameCS(), Arrays.asList(new String[] {opName}));
        
        ImplicitOperationCallOclExpressionCase.getInstance().out(node, resultMap, env);
        
        OperationCallExp expectedResult = new OperationCallExp();
        List<Classifier> types = Collections.emptyList();
        expectedResult.setReferredOperation(env.lookupImplicitOperation(opName, types));
        List<OclExpression> args = Collections.emptyList();
        expectedResult.setArguments(args);
        expectedResult.setSource(env.findImplicitSourceForOperation(opName, types).accept(ImplicitSourceToOclExpression.instance, node));
        
        expectedMap.put(node, expectedResult);
        
        checkMaps();
    }
    
    @Test
    public void testOut_withArgs() throws AmbiguosOperationCall {
        String opName = "opWithArgs";
        AImplicitOperationCallOclExpressionCS node = new AImplicitOperationCallOclExpressionCS();
        
        OperationCallExp expectedResult = new OperationCallExp();
        List<Classifier> types = new ArrayList<Classifier>(1);
        List<OclExpression> args = new ArrayList<OclExpression>(1);
        args.add(new BooleanLiteralExp(Boolean.TRUE));
        types.add(args.get(0).getType());
        
        expectedResult.setReferredOperation(env.lookupImplicitOperation(opName, types));
        expectedResult.setArguments(args);
        expectedResult.setSource(env.findImplicitSourceForOperation(opName, types).accept(ImplicitSourceToOclExpression.instance, node));
        
        node.setNameCS(new ASimpleNameNameCS(new ASimpleSimpleNameCS(new TIdentifier(opName))));
        node.setArgs(Arrays.asList(new POclExpressionCS[] {new AAndOclExpressionCS()}));
        
        resultMap.put(node.getNameCS(), Arrays.asList(new String[] {opName}));
        resultMap.put(node.getArgs().get(0), args.get(0).clone());
        expectedMap.put(node, expectedResult);
        
        ImplicitOperationCallOclExpressionCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }
}
