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
public abstract class AbstractTernaryOperationTest extends AbstractTest {

    Operation op;
    Variable sourceVar;
    Variable arg1Var;
    Variable arg2Var;
    VariableTable varTable;
    String expToParse;

    public AbstractTernaryOperationTest(Operation opToTest) {
        this.op = opToTest;

        sourceVar = new Variable("source", null, getSourceType(op));
        arg1Var = new Variable("arg1", null, getArg1Type(op));
        arg2Var = new Variable("arg2", null, getArg2Type(op));

        varTable = new VariableTable();
        try {
            varTable.createVariable(sourceVar, InvalidValue.instantiate());
            varTable.createVariable(arg1Var, InvalidValue.instantiate());
            varTable.createVariable(arg2Var, InvalidValue.instantiate());
        } catch (VariableAlreadyExistException ex) {
            //this should never happend!
            throw new AssertionError();
        }

        expToParse = getExpressionToParse("source", "arg1", "arg2");
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
    protected Classifier getArg1Type(Operation op) {
        return op.getOwnedParameters().get(0).getType();
    }

    /**
     * This method is overridable to allow change the variable type.
     *
     * @param op
     *
     * @return
     */
    protected Classifier getArg2Type(Operation op) {
        return op.getOwnedParameters().get(1).getType();
    }

    String getExpressionToParse(String sVarName, String aVar1Name, String aVar2Name) {
        return sVarName + "." + op.getName() + "(" + aVar1Name + ", " + aVar2Name + ")";
    }

    /**
     * Gets the expression that is going to be shown to user when the test fails. When a test fail, the
     * following message is shown: [exp] = [resultVal] (expecter = [expectedVal])
     *
     * When: <ul> <li> [exp] : value returned by this method <li> [resultVal] : calculated value <li>
     * [expectedVal] : expected value </ul>
     *
     * @param sourceVal
     * @param arg1Val
     * @param arg2Val
     *
     * @return an user friendly expression that will be shown to user when the test fail
     */
    protected String getLiteralExp(OclValue<?> sourceVal, OclValue<?> arg1Val, OclValue<?> arg2Val) {
        return sourceVal + "." + op.getName() + "(" + arg1Val + ", " + arg2Val + ")";
    }

    public void executeTest(OclValue<?> sourceVal, OclValue<?> arg1Val, OclValue<?> arg2Val, OclValue<?> expected) throws Exception {
        varTable.setValue(sourceVar.getName(), sourceVal);
        varTable.setValue(arg1Var.getName(), arg1Val);
        varTable.setValue(arg2Var.getName(), arg2Val);

        OperationCallExp exp = (OperationCallExp) TesterTool.parse(expToParse, varTable);

        assert op.getTemplateOperation().equals(exp.getReferredOperation().getTemplateOperation()) : "This test should test " + op.getTemplateOperation() + ", but "
                + exp.getReferredOperation().getTemplateOperation() + " is the real executed expression "
                + "when source = " + sourceVal + " and arg = " + arg1Val;

        OclValue<?> result = TesterTool.evaluate(exp, varTable, false);

        assert result.equals(expected) : getLiteralExp(sourceVal, arg1Val, arg2Val) + " = " + result + "(expected = " + expected + ")";
    }

    public abstract static class AbstractCollectionTernaryOperationTest extends AbstractTernaryOperationTest {

        public AbstractCollectionTernaryOperationTest(Operation opToTest) {
            super(opToTest);
        }

        @Override
        protected String getExpressionToParse(String sVarName, String aVar1Name, String aVar2Name) {
            return sVarName + "->" + op.getName() + "(" + aVar1Name + ", " + aVar2Name + ")";
        }

        @Override
        protected String getLiteralExp(OclValue<?> sourceVal, OclValue<?> arg1Val, OclValue<?> arg2Val) {
        return sourceVal + "->" + op.getName() + "(" + arg1Val + ", " + arg2Val + ")";
    }
    }
}
