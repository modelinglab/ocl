/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.vartables.VariableAlreadyExistException;
import org.modelinglab.ocl.core.vartables.VariableTable;
import org.modelinglab.ocl.evaluator.AbstractTest;
import org.modelinglab.ocl.evaluator.TesterTool;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public abstract class AbstractUnaryOperationTest extends AbstractTest {

    Operation op;
    Variable sourceVar;
    VariableTable varTable;
    String expToParse;
    
    public AbstractUnaryOperationTest(Operation opToTest) {
        this.op = opToTest;
        
        sourceVar = new Variable("source", null, opToTest.getSource());
        
        varTable = new VariableTable();
        try {
            varTable.createVariable(sourceVar, InvalidValue.instantiate());
        } catch (VariableAlreadyExistException ex) {
            //this should never happend!
            throw new AssertionError();
        }
        
        expToParse = getExpressionToParse(getSourceVarName());
    }

    /**
     * @param source the name of the source variable
     * @param aVarName the name of the arg variable
     *
     * @return the expression that is going to be evaluated
     */
    protected String getExpressionToParse(String source) {
        return source + '.' + op.getName() + "()";
    }

    /**
     * Gets the expression that is going to be shown to user when the test fails. When a test fail, the
     * following message is shown: [exp] = [resultVal] (expecter = [expectedVal])
     *
     * When: <ul> <li> [exp] : value returned by this method <li> [resultVal] : calculated value <li>
     * [expectedVal] : expected value </ul>
     *
     * @param sourceVal
     * @param argVal
     *
     * @return an user friendly expression that will be shown to user when the test fail
     */
    protected String getLiteralExp(OclValue<?> sourceVal) {
        return sourceVal + "." + op.getName() + "()";
    }

    protected String getSourceVarName() {
        return "source";
    }

    public void executeTest(OclValue<?> sourceVal, OclValue<?> expected) throws Exception {

        varTable.setValue(sourceVar.getName(), sourceVal);

        OperationCallExp exp = (OperationCallExp) TesterTool.parse(expToParse, varTable);

        assert op.getTemplateOperation().equals(exp.getReferredOperation().getTemplateOperation())
                : "This test should test "+op.getTemplateOperation() +", but " 
                + exp.getReferredOperation().getTemplateOperation() + " is the real executed expression "
                + "when source = "+sourceVal;

        OclValue<?> result = TesterTool.evaluate(exp, varTable, false);

        assert result.equals(expected) : getLiteralExp(sourceVal) + " = " + result + "(expected = " + expected + ")";
        
    }
    
    public static abstract class AbstractCollectionUnaryOperationTest extends AbstractUnaryOperationTest {

        public AbstractCollectionUnaryOperationTest(Operation opToTest) {
            super(opToTest);
        }

        @Override
        protected String getExpressionToParse(String sVarName) {
            return sVarName + "->" + op.getName() + "()";
        }

        @Override
        protected String getLiteralExp(OclValue<?> sourceVal) {
            return sourceVal + "->" + op.getName() + "()";
        }
        
    }
}
