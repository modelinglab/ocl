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
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.parser.CompanyEnvironment;
import org.modelinglab.ocl.parser.ParserTesterTool;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class LiteralExpTest {
    
    @Test
    public void integerLiteralTest() throws Exception {
        final Long number = 3l;
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        
        IntegerLiteralExp expectedExp = new IntegerLiteralExp(number);
        
        ParserTesterTool.testParser(number.toString(), expectedExp, env);
        
    }
    
    @Test
    public void realLiteralTest() throws Exception {
        final Double number = 3d;
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        
        RealLiteralExp expectedExp = new RealLiteralExp(number);
        
        ParserTesterTool.testParser(number.toString(), expectedExp, env);
    }
    
    @Test
    public void stringLiteralTest() throws Exception {
        final String string = "This is a Test. This is an special word: null";
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        
        StringLiteralExp expectedExp = new StringLiteralExp(string);
        
        ParserTesterTool.testParser("'"+string+"'", expectedExp, env);
        ParserTesterTool.testParser("\""+string+"\"", expectedExp, env);
    }
    
    @Test
    public void booleanLiteralTest() throws Exception {
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        
        BooleanLiteralExp expectedExpTrue = new BooleanLiteralExp(Boolean.TRUE);
        BooleanLiteralExp expectedExpFalse = new BooleanLiteralExp(Boolean.FALSE);
        
        ParserTesterTool.testParser("true", expectedExpTrue, env);
        ParserTesterTool.testParser("false", expectedExpFalse, env);
    }
    
    @Test
    public void CollectionLiteralTest() throws Exception {
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        
        String expressionToTest;
        expressionToTest = "Set{1, 2 + 3, 4..7}";
        
        /*
         * expected type
         */
        Classifier intType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER);
        SetType expectedType = new SetType(intType);
        
        CollectionLiteralExp expected = new CollectionLiteralExp();
        expected.setType(expectedType);
        expected.addPart(new CollectionItem(new IntegerLiteralExp(1l)));
        
        List<Classifier> argTypes = Arrays.asList(new Classifier[] {intType});
        Operation add = env.lookupOperation(intType, "+", argTypes);
        OperationCallExp opCall = new OperationCallExp();
        opCall.setSource(new IntegerLiteralExp(2l));
        opCall.addArgument(new IntegerLiteralExp(3l));
        opCall.setReferredOperation(add);
        expected.addPart(new CollectionItem(opCall));
        
        expected.addPart(new CollectionRange(new IntegerLiteralExp(4l), new IntegerLiteralExp(7l)));
        
        ParserTesterTool.testParser(expressionToTest, expected, env);
    }
}
