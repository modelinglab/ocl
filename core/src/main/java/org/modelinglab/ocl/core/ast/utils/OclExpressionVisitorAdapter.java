/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.expressions.InvalidLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.PrimitiveLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.LiteralExp;
import org.modelinglab.ocl.core.ast.expressions.TupleLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.CollectionLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.expressions.CallExp;
import org.modelinglab.ocl.core.ast.expressions.TupleAttributeCallExp;
import org.modelinglab.ocl.core.ast.expressions.LetExp;
import org.modelinglab.ocl.core.ast.expressions.RealLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.VariableExp;
import org.modelinglab.ocl.core.ast.expressions.StringLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.FeatureCallExp;
import org.modelinglab.ocl.core.ast.expressions.UnlimitedNaturalExp;
import org.modelinglab.ocl.core.ast.expressions.TypeExp;
import org.modelinglab.ocl.core.ast.expressions.AssociationEndCallExp;
import org.modelinglab.ocl.core.ast.expressions.EnumLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.StateExp;
import org.modelinglab.ocl.core.ast.expressions.IterateExp;
import org.modelinglab.ocl.core.ast.expressions.NavigationCallExp;
import org.modelinglab.ocl.core.ast.expressions.MessageExp;
import org.modelinglab.ocl.core.ast.expressions.IfExp;
import org.modelinglab.ocl.core.ast.expressions.LoopExp;
import org.modelinglab.ocl.core.ast.expressions.NullLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.BooleanLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.AttributeCallExp;
import org.modelinglab.ocl.core.ast.expressions.IntegerLiteralExp;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclExpressionVisitorAdapter<Result, Arg> implements OclExpressionsVisitor<Result, Arg> {

    protected Result visitOclExpression(OclExpression exp, Arg argument) {
        return null;
    }

    protected Result visitCallExp(CallExp exp, Arg argument) {
        return visitOclExpression(exp, argument);
    }

    protected Result visitFeatureCallExp(FeatureCallExp exp, Arg argument) {
        return visitCallExp(exp, argument);
    }

    protected Result visitNavigationCallExp(NavigationCallExp exp, Arg argument) {
        return visitFeatureCallExp(exp, argument);
    }

    @Override
    public Result visit(AssociationEndCallExp exp, Arg argument) {
        return visitNavigationCallExp(exp, argument);
    }

    @Override
    public Result visit(AttributeCallExp exp, Arg argument) {
        return visitFeatureCallExp(exp, argument);
    }

    protected Result visitLiteralExp(LiteralExp exp, Arg argument) {
        return visitOclExpression(exp, argument);
    }

    protected Result visitPrimitiveLiteralExp(PrimitiveLiteralExp exp, Arg argument) {
        return visitLiteralExp(exp, argument);
    }

    @Override
    public Result visit(BooleanLiteralExp exp, Arg argument) {
        return visitPrimitiveLiteralExp(exp, argument);
    }

    @Override
    public Result visit(CollectionLiteralExp exp, Arg argument) {
        return visitLiteralExp(exp, argument);
    }

    @Override
    public Result visit(EnumLiteralExp exp, Arg argument) {
        return visitLiteralExp(exp, argument);
    }

    @Override
    public Result visit(IfExp exp, Arg argument) {
        return visitOclExpression(exp, argument);
    }

    @Override
    public Result visit(IntegerLiteralExp exp, Arg argument) {
        return visitPrimitiveLiteralExp(exp, argument);
    }

    @Override
    public Result visit(InvalidLiteralExp exp, Arg argument) {
        return visitOclExpression(exp, argument);
    }

    protected Result visitLoopExp(LoopExp exp, Arg argument) {
        return visitCallExp(exp, argument);
    }

    @Override
    public Result visit(IterateExp exp, Arg argument) {
        return visitLoopExp(exp, argument);
    }

    @Override
    public Result visit(IteratorExp exp, Arg argument) {
        return visitLoopExp(exp, argument);
    }

    @Override
    public Result visit(LetExp exp, Arg argument) {
        return visitOclExpression(exp, argument);
    }

    @Override
    public Result visit(MessageExp exp, Arg argument) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Result visit(NullLiteralExp exp, Arg argument) {
        return visitLiteralExp(exp, argument);
    }

    @Override
    public Result visit(OperationCallExp exp, Arg argument) {
        return visitFeatureCallExp(exp, argument);
    }

    @Override
    public Result visit(RealLiteralExp exp, Arg argument) {
        return visitPrimitiveLiteralExp(exp, argument);
    }

    @Override
    public Result visit(StateExp exp, Arg argument) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Result visit(StringLiteralExp exp, Arg argument) {
        return visitPrimitiveLiteralExp(exp, argument);
    }

    @Override
    public Result visit(TupleAttributeCallExp exp, Arg argument) {
        return visitCallExp(exp, argument);
    }

    @Override
    public Result visit(TupleLiteralExp exp, Arg argument) {
        return visitLiteralExp(exp, argument);
    }

    @Override
    public Result visit(TypeExp exp, Arg argument) {
        return visitOclExpression(exp, argument);
    }

    @Override
    public Result visit(UnlimitedNaturalExp exp, Arg argument) {
        return visitPrimitiveLiteralExp(exp, argument);
    }

    @Override
    public Result visit(VariableExp exp, Arg argument) {
        return visitOclExpression(exp, argument);
    }

    @Override
    public Result visit(Variable var, Arg argument) {
        return null;
    }
}
