/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.utils.analysis;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.utils.OclAnalysis;
import org.modelinglab.ocl.core.ast.utils.OclExpressionDFW;
import org.modelinglab.ocl.core.ast.utils.OclExpressionVisitorAdapter;
import org.modelinglab.ocl.core.exceptions.OclException;
import org.modelinglab.ocl.core.exceptions.OclRuntimeException;

/**
 *
 */
public class AdvancedStaticTypeCheck implements OclAnalysis {

    boolean onlyAnalyzeRoot;

    /**
     * Creates a new analysis.
     * The value of {@link #onlyAnalyzeRoot} is set to false.
     */
    public AdvancedStaticTypeCheck() {
        onlyAnalyzeRoot = false;
    }

    public AdvancedStaticTypeCheck(boolean onlyAnalyzeRoot) {
        this.onlyAnalyzeRoot = onlyAnalyzeRoot;
    }

    public boolean isOnlyAnalyzeRoot() {
        return onlyAnalyzeRoot;
    }

    public void setOnlyAnalyzeRoot(boolean onlyAnalyzeRoot) {
        this.onlyAnalyzeRoot = onlyAnalyzeRoot;
    }

    @Override
    public void analyze(OclExpression exp) throws OclException {
        try {
            if (onlyAnalyzeRoot) {
                Visitor v = new Visitor();
                exp.accept(v, null);
            } else {
                Walker w = new Walker();
                exp.accept(w, null);
            }
        } catch (OclRuntimeException ex) {
            throw new OclException(ex);
        }
    }

    private static void checkOperationCallExp(OperationCallExp exp) {
        Operation op = exp.getReferredOperation();

        switch (op.getName()) {
            case "<>":
            case "=": {
                isEqualOrDifferentCase(exp);
                break;
            }
            case "oclAsType":
            case "oclIsKindOf": {
                isKindOfAndAsTypeCase(exp);
                break;
            }
            case "oclIsTypeOf": {
                isTypeOfCase(exp);
                break;
            }
        }
    }

    private static void isEqualOrDifferentCase(OperationCallExp exp) {
        Classifier sourceType = exp.getSource().getType();

        assert exp.getArguments().size() == 1;
        Classifier argType = exp.getArguments().get(0).getType();

        if (sourceType.conformsTo(argType)) {
            return;
        }
        if (argType.conformsTo(sourceType)) {
            return;
        }
        throw new OclRuntimeException(exp.toText() + " is invalid because incompatible types.");
    }

    private static void isKindOfAndAsTypeCase(OperationCallExp exp) {
        Classifier sourceType = exp.getSource().getType();

        assert exp.getArguments().size() == 1;
        assert exp.getArguments().get(0).getType() instanceof ClassifierType;
        ClassifierType castTo = (ClassifierType) exp.getArguments().get(0).getType();

        if (sourceType.conformsTo(castTo)) { //this expression is going to be evaluated always to true, an "ascending" casting
            return;
        }
        if (castTo.conformsTo(sourceType)) { //this is an "descending" casting
            return;
        }
        throw new OclRuntimeException(exp.toText() + " is invalid because incompatible types.");
    }

    private static void isTypeOfCase(OperationCallExp exp) {
        Classifier sourceType = exp.getSource().getType();

        assert exp.getArguments().size() == 1;
        assert exp.getArguments().get(0).getType() instanceof ClassifierType;
        ClassifierType castTo = (ClassifierType) exp.getArguments().get(0).getType();

        if (castTo.conformsTo(sourceType)) { //this is an "descending" casting
            return;
        }
        throw new OclRuntimeException(exp.toText() + " is invalid because incompatible types.");
    }

    private static class Visitor extends OclExpressionVisitorAdapter<Void, Void> {

        @Override
        public Void visit(OperationCallExp exp, Void argument) {
            checkOperationCallExp(exp);
            return null;
        }
    }

    private static class Walker extends OclExpressionDFW<Void> {

        Visitor visitor;

        public Walker() {
            visitor = new Visitor();
        }

        @Override
        protected void preOclExpression(OclExpression exp, Void arguments) {
            exp.accept(visitor, null);
        }
    }
}
