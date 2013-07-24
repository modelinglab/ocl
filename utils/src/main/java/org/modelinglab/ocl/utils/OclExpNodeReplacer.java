/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.utils;

import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.utils.OclExpressionDFW;

/**
 * Replace all occurrences of some given ocl expression by another in the visited ocl expression.
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@ThreadSafe
public class OclExpNodeReplacer implements Serializable {

    private static final long serialVersionUID = 1L;
    private final OclExpNodeReplacerFunction replaceFunction;

    public OclExpNodeReplacer(OclExpNodeReplacerFunction replaceFunction) {
        this.replaceFunction = replaceFunction;
    }

    public void replace(OclExpWrapper wrappedExpressionToReplace) {
        OclExpression expToReplace = wrappedExpressionToReplace.getReplaceTo();
        
        boolean originalReplaced = replaceFunction.evaluateNode(wrappedExpressionToReplace.getReplaceTo(), wrappedExpressionToReplace);
        
        if (!originalReplaced) { //then we are going to try to replace original expression children
            expToReplace.accept(new OclExpNodeReplacerDFW(), wrappedExpressionToReplace);
            wrappedExpressionToReplace.setReplaceTo(expToReplace);
        }
    }

    private class OclExpNodeReplacerDFW extends OclExpressionDFW<OclExpWrapper> {

        @Override
        protected void postCallExp(CallExp exp, OclExpWrapper arguments) {
            if (exp.getSource() != null) {
                if (replaceFunction.evaluateNode(exp.getSource(), arguments)) {
                    exp.setSource(arguments.replaceTo);
                }
            }
        }

        @Override
        protected void postCollectionItem(CollectionItem exp, OclExpWrapper arguments) {
            if (exp.getItem() != null) {
                if (replaceFunction.evaluateNode(exp.getItem(), arguments)) {
                    exp.setItem(arguments.replaceTo);
                }
            }
        }

        @Override
        protected void postCollectionRange(CollectionRange exp, OclExpWrapper arguments) {
            if (exp.getFirst() != null) {
                if (replaceFunction.evaluateNode(exp.getFirst(), arguments)) {
                    exp.setFirst(arguments.replaceTo);
                }
            }
            if (exp.getLast() != null) {
                if (replaceFunction.evaluateNode(exp.getLast(), arguments)) {
                    exp.setLast(arguments.replaceTo);
                }
            }
        }

        @Override
        protected void postIfExp(IfExp exp, OclExpWrapper arguments) {
            if (exp.getCondition() != null) {
                if (replaceFunction.evaluateNode(exp.getCondition(), arguments)) {
                    exp.setCondition(arguments.replaceTo);
                }
            }
            if (exp.getThenExpression() != null) {
                if (replaceFunction.evaluateNode(exp.getThenExpression(), arguments)) {
                    exp.setThenExpression(arguments.replaceTo);
                }
            }
            if (exp.getElseExpression() != null) {
                if (replaceFunction.evaluateNode(exp.getElseExpression(), arguments)) {
                    exp.setElseExpression(arguments.replaceTo);
                }
            }
        }

        @Override
        protected void postLetExp(LetExp exp, OclExpWrapper arguments) {
            if (exp.getIn() != null) {
                if (replaceFunction.evaluateNode(exp.getIn(), arguments)) {
                    exp.setIn(arguments.replaceTo);
                }
            }
        }

        @Override
        protected void postLoopExp(LoopExp exp, OclExpWrapper arguments) {
            if (exp.getBody() != null) {
                if (replaceFunction.evaluateNode(exp.getBody(), arguments)) {
                    exp.setBody(arguments.replaceTo);
                }
            }
        }

        @Override
        protected void postNavigationCallExp(NavigationCallExp exp, OclExpWrapper arguments) {
            for (OclExpression qualifier : exp.getQualifiers()) {
                if (qualifier != null) {
                    if (replaceFunction.evaluateNode(qualifier, arguments)) {
                        if (arguments.replaceTo == null) {
                            exp.removeQualifier(qualifier);
                        }
                        else {
                            exp.replaceQualifier(qualifier, arguments.replaceTo);
                        }
                    }
                }
            }
        }

        @Override
        protected void postOperationCallExp(OperationCallExp exp, OclExpWrapper arguments) {
            for (OclExpression arg : exp.getArguments()) {
                if (arg != null) {
                    if (replaceFunction.evaluateNode(arg, arguments)) {
                        if (arguments.replaceTo == null) {
                            exp.removeArgument(arg);
                        }
                        else {
                            exp.replaceArgument(arg, arguments.replaceTo);
                        }
                    }
                }
            }
        }

        @Override
        protected void postVariable(Variable var, OclExpWrapper arguments) {
            if (var.getInitExpression() != null) {
                if (replaceFunction.evaluateNode(var.getInitExpression(), arguments)) {
                    var.setInitExpression(arguments.replaceTo);
                }
            }
        }
    }

    public static interface OclExpNodeReplacerFunction {
        /**
         * This methos is called by OclExpressionNodeReplacer to known if the visited expression
         * should be replaced or not. If this method return true, node will be replace with arg.getReplaceTo
         * @param node the expression we want to know whether to be replaced or not
         * @param arg a wrapper that wrap an oclExpression
         * @return true if 'node' should be replaced
         * @see OclExpWrapper#replaceTo
         */
        public boolean evaluateNode(@Nonnull OclExpression node, @Nonnull OclExpWrapper arg);
    }

    /**
     * This class is used to wrap an OclExpression (or null) as inout argument of 
     * {@link OclExpNodeReplacerFunction#evaluateNode(org.modelinglab.ocl.core.ast.expressions.OclExpression, org.modelinglab.ocl.utils.OclExpNodeReplacer.OclExpWrapper) }
     */
    public static class OclExpWrapper {

        private OclExpression replaceTo;

        @Nullable
        public OclExpression getReplaceTo() {
            return replaceTo;
        }

        public void setReplaceTo(@Nullable OclExpression replaceTo) {
            this.replaceTo = replaceTo;
        }
    }
}
