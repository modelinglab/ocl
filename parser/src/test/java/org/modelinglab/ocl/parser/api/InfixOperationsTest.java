/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.api;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.IntegerLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.expressions.RealLiteralExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.parser.CompanyEnvironment;
import org.modelinglab.ocl.parser.ParserTesterTool;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class InfixOperationsTest {
    
    @Test
    public void testAddReal() throws Exception {
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        final String expressionToTest = "2 + 3 + 4.0";
        
        Classifier intType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER);
        Classifier realType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL);
        
        List<Classifier> argTypes = Arrays.asList(new Classifier[] {intType});
        Operation addInt = env.lookupOperation(intType, "+", argTypes);
        assert addInt != null;
        Operation addReal = env.lookupOperation(realType, "+", argTypes);
        assert addReal != null;
        
        OperationCallExp opCallLeaf = new OperationCallExp();
        opCallLeaf.setSource(new IntegerLiteralExp(2l));
        opCallLeaf.addArgument(new IntegerLiteralExp(3l));
        opCallLeaf.setReferredOperation(addInt);
        
        OperationCallExp expected = new OperationCallExp();
        expected.setSource(opCallLeaf);
        expected.addArgument(new RealLiteralExp(4d));
        expected.setReferredOperation(addReal);
        
        ParserTesterTool.testParser(expressionToTest, expected, env);        
    }
    
    @Test
    public void testAddInt() throws Exception {
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        final String expressionToTest = "2 + 3 + 4";
        
        Classifier intType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER);
        
        List<Classifier> argTypes = Arrays.asList(new Classifier[] {intType});
        Operation add = env.lookupOperation(intType, "+", argTypes);
        
        OperationCallExp opCallLeaf = new OperationCallExp();
        opCallLeaf.setSource(new IntegerLiteralExp(2l));
        opCallLeaf.addArgument(new IntegerLiteralExp(3l));
        opCallLeaf.setReferredOperation(add);
        
        OperationCallExp expected = new OperationCallExp();
        expected.setSource(opCallLeaf);
        expected.addArgument(new IntegerLiteralExp(4l));
        expected.setReferredOperation(add);
        
        ParserTesterTool.testParser(expressionToTest, expected, env);        
    }
    
    @Test
    public void testAddSubDivMult() throws Exception {
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        final String expressionToTest = "3 + 4 - 5 / 10 * 5";
        
        Classifier intType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER);
        Classifier realType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL);
        
        List<Classifier> intArgTypes = Arrays.asList(new Classifier[] {intType});
        List<Classifier> realArgTypes = Arrays.asList(new Classifier[] {realType});
        
        Operation mul = env.lookupOperation(realType, "*", realArgTypes);
        assert mul != null;
        Operation div = env.lookupOperation(intType, "/", intArgTypes);
        assert div != null;
        Operation sub = env.lookupOperation(intType, "-", realArgTypes);
        assert sub != null;
        Operation add = env.lookupOperation(intType, "+", intArgTypes);
        assert add != null;

        OperationCallExp divCall = new OperationCallExp();
        divCall.setSource(new IntegerLiteralExp(5l));
        divCall.addArgument(new IntegerLiteralExp(10l));
        divCall.setReferredOperation(div);
        
        OperationCallExp mulCall = new OperationCallExp();
        mulCall.setSource(divCall);
        mulCall.addArgument(new IntegerLiteralExp(5l));
        mulCall.setReferredOperation(mul);
        
        OperationCallExp addCall = new OperationCallExp();
        addCall.setSource(new IntegerLiteralExp(3l));
        addCall.addArgument(new IntegerLiteralExp(4l));
        addCall.setReferredOperation(add);
        
        OperationCallExp subCall = new OperationCallExp();
        subCall.setSource(addCall);
        subCall.addArgument(mulCall);
        subCall.setReferredOperation(sub);
        
        OperationCallExp expected = subCall;
        
        ParserTesterTool.testParser(expressionToTest, expected, env);        
    }
}
