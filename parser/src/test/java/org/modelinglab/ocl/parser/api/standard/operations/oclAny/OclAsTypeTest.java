/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.api.standard.operations.oclAny;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.NullLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.expressions.TypeExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.parser.CompanyEnvironment;
import org.modelinglab.ocl.parser.ParserTesterTool;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclAsTypeTest {
    
    @Test
    public void oclAsType() throws Exception {
        StaticEnvironment env = CompanyEnvironment.createEnvironment();
        final String expressionToTest = "null.oclAsType(Person)";
        
        /*
         * Lookup person type
         */
        UmlClass personType = (UmlClass) env.lookup("Person");
        assert personType != null;
        
        /*
         * lookup operation
         */
        List<Classifier> argTypes = Arrays.asList(new Classifier[] {personType.getClassifierType()});
        Operation op = env.lookupOperation(VoidType.getInstance(), "oclAsType", argTypes);
        assert op != null;
        
        /*
         * Person TypeExp creation
         */
        TypeExp arg = personType.getClassifierType().createExpression();
        
        /*
         * OperationCallExp creation
         */
        OperationCallExp expected = new OperationCallExp();
        expected.setSource(new NullLiteralExp());
        expected.setReferredOperation(op);
        expected.addArgument(arg);
        
        ParserTesterTool.testParser(expressionToTest, expected, env);
        assert expected.getType().conformsTo(personType);
    }
}
