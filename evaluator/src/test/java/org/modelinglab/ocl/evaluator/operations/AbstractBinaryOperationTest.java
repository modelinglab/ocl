/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.Classifier;
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
public abstract class AbstractBinaryOperationTest extends AbstractTest {

    Operation op;
    Variable sourceVar;
    Variable argVar;
    VariableTable varTable;
    String expToParse;

    public AbstractBinaryOperationTest(Operation opToTest) {
        this.op = opToTest;

        sourceVar = new Variable("source", null, getSourceType(op));
        argVar = new Variable("arg", null, getArgType(op));

        varTable = new VariableTable();
        try {
            varTable.createVariable(sourceVar, InvalidValue.instantiate());
            varTable.createVariable(argVar, InvalidValue.instantiate());
        } catch (VariableAlreadyExistException ex) {
            //this should never happend!
            throw new AssertionError();
        }

        expToParse = getExpressionToParse(getSourceVarName(), getArgVarName());
    }

    /**
     * This method is overridable to allow change the variable type.
     *
     * @param op
     *
     * @return
     */
    protected Classifier getSourceType(Operation op) {
        return op.getSource();
    }

    /**
     * This method is overridable to allow change the variable type.
     *
     * @param op
     *
     * @return
     */
    protected Classifier getArgType(Operation op) {
        return op.getOwnedParameters().get(0).getType();
    }

    /**
     * @param sVarName the name of the source variable
     * @param aVarName the name of the arg variable
     *
     * @return the expression that is going to be evaluated
     */
    protected String getExpressionToParse(String sVarName, String aVarName) {
        return sVarName + '.' + op.getName() + "(" + aVarName + ")";
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
    protected String getLiteralExp(OclValue<?> sourceVal, OclValue<?> argVal) {
        return sourceVal + "." + op.getName() + "(" + argVal + ")";
    }

    protected String getSourceVarName() {
        return "source";
    }

    protected String getArgVarName() {
        return "arg";
    }

    public void executeTest(OclValue<?> sourceVal, OclValue<?> argVal, OclValue<?> expected) throws Exception {
        varTable.setValue(sourceVar.getName(), sourceVal);
        varTable.setValue(argVar.getName(), argVal);

        OperationCallExp exp = (OperationCallExp) TesterTool.parse(expToParse, varTable);

        assert op.getTemplateOperation().equals(exp.getReferredOperation().getTemplateOperation()) : "This test should test " + op.getTemplateOperation() + ", but "
                + exp.getReferredOperation().getTemplateOperation() + " is the real executed expression "
                + "when source = " + sourceVal + " and arg = " + argVal;

        OclValue<?> result = TesterTool.evaluate(exp, varTable);

        assert result.equals(expected) : getLiteralExp(sourceVal, argVal) + " = " + result + "(expected = " + expected + ")";
    }

    public abstract static class AbstractInfixOperationTest extends AbstractBinaryOperationTest {

        public AbstractInfixOperationTest(Operation opToTest) {
            super(opToTest);
        }

        @Override
        protected String getExpressionToParse(String sVarName, String aVarName) {
            return sVarName + " " + op.getName() + " " + aVarName;
        }

        @Override
        protected String getLiteralExp(OclValue<?> sourceVal, OclValue<?> argVal) {
            return sourceVal + " " + op.getName() + " " + argVal;
        }
    }

    public abstract static class AbstractCollectionBinaryOperationTest extends AbstractBinaryOperationTest {

        public AbstractCollectionBinaryOperationTest(Operation opToTest) {
            super(opToTest);
        }

        @Override
        protected String getExpressionToParse(String sVarName, String aVarName) {
            return sVarName + "->" + op.getName() + "(" + aVarName + ")";
        }

        @Override
        protected String getLiteralExp(OclValue<?> sourceVal, OclValue<?> argVal) {
            return sourceVal + "->" + op.getName() + "(" + argVal + ")";
        }
    }
}
