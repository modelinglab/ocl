/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.api;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.TupleAttributeCallExp;
import org.modelinglab.ocl.parser.CompanyEnvironment;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.ParserTesterTool;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TupleTest {
    
    @Test
    public void tupleAttributeCallExpTest() throws Exception {
        final String tupleLiteral = "Tuple{a : Integer = 2, b : String = \"text\"}";
        final String expression = tupleLiteral + ".a";
        final StaticEnvironment env = CompanyEnvironment.createEnvironment();
        
        TupleAttributeCallExp expected = new TupleAttributeCallExp();
        expected.setTupleAttributeId("a");
        expected.setSource(ParserTesterTool.parse(tupleLiteral, env));
        
        ParserTesterTool.testParser(expression, expected, env);        
    }
    
    @Test
    public void tupleAttributeCallExp_failTest() throws Exception {
        final String expression = "Tuple{a : Integer = 2, b : String = \"text\"}.notAttribute";
        final StaticEnvironment env = CompanyEnvironment.createEnvironment();
        
        try {
            ParserTesterTool.parse(expression, env);
            assert false : "Parser should fail when referred tupleAttributeId is not referred to an "+
                    "attribute contained by tuple source type.";
        }
        catch (OclParserException ex) {
        }
    }
}
