/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.expressions.PrimitiveLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.InvalidLiteralExp;
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
import org.modelinglab.ocl.core.ast.expressions.CollectionLiteralPart;
import org.modelinglab.ocl.core.ast.expressions.MessageExp;
import org.modelinglab.ocl.core.ast.expressions.IfExp;
import org.modelinglab.ocl.core.ast.expressions.NumericLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.LoopExp;
import org.modelinglab.ocl.core.ast.expressions.NullLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.BooleanLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.CollectionRange;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.AttributeCallExp;
import org.modelinglab.ocl.core.ast.expressions.IntegerLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.CollectionItem;
import org.modelinglab.ocl.core.ast.expressions.TupleLiteralPart;

/**
 *
 * @param <Arg>
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclExpressionDFW<Arg> implements OclExpressionsVisitor<Void, Arg>, CollectionLiteralPartVisitor<Void, Arg>, TupleLiteralPartVisitor<Void, Arg> {

    protected void preAssociationEndClassCallExp(AssociationEndCallExp exp, Arg arguments) {
    }

    protected void postAssociationEndClassCallExp(AssociationEndCallExp exp, Arg arguments) {
    }

    @Override
    public Void visit(AssociationEndCallExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preCallExp(exp, arguments);
        preFeatureCallExp(exp, arguments);
        preNavigationCallExp(exp, arguments);
        preAssociationEndClassCallExp(exp, arguments);

        if (exp.getSource() != null) {
            exp.getSource().accept(this, arguments);
        }

        postOclExpression(exp, arguments);
        postCallExp(exp, arguments);
        postFeatureCallExp(exp, arguments);
        postNavigationCallExp(exp, arguments);
        postAssociationEndClassCallExp(exp, arguments);
        return null;
    }

    protected void preAttributeCallExp(AttributeCallExp exp, Arg arguments) {
    }

    protected void postAttributeCallExp(AttributeCallExp exp, Arg arguments) {
    }

    @Override
    public Void visit(AttributeCallExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preCallExp(exp, arguments);
        preFeatureCallExp(exp, arguments);
        preAttributeCallExp(exp, arguments);

        if (exp.getSource() != null) {
            exp.getSource().accept(this, arguments);
        }

        postOclExpression(exp, arguments);
        postCallExp(exp, arguments);
        postFeatureCallExp(exp, arguments);
        postAttributeCallExp(exp, arguments);

        return null;
    }

    protected void preBooleanLiteralExp(BooleanLiteralExp exp, Arg arguments) {
    }

    protected void postBooleanLiteralExp(BooleanLiteralExp exp, Arg arguments) {
    }

    @Override
    public Void visit(BooleanLiteralExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preLiteralExp(exp, arguments);
        prePrimitiveLiteralExp(exp, arguments);
        preBooleanLiteralExp(exp, arguments);

        postOclExpression(exp, arguments);
        postLiteralExp(exp, arguments);
        postPrimitiveLiteralExp(exp, arguments);
        postBooleanLiteralExp(exp, arguments);

        return null;
    }

    protected void preCallExp(CallExp exp, Arg arguments) {
    }

    protected void postCallExp(CallExp exp, Arg arguments) {
    }

    protected void preCollectionLiteralExp(CollectionLiteralExp exp, Arg arguments) {
    }

    protected void postCollectionLiteralExp(CollectionLiteralExp exp, Arg arguments) {
    }

    @Override
    public Void visit(CollectionLiteralExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preLiteralExp(exp, arguments);
        preCollectionLiteralExp(exp, arguments);

        for (CollectionLiteralPart part : exp.getParts()) {
            part.accept(this, arguments);
        }

        postOclExpression(exp, arguments);
        postLiteralExp(exp, arguments);
        postCollectionLiteralExp(exp, arguments);
        return null;
    }

    protected void preEnumLiteralExp(EnumLiteralExp exp, Arg arguments) {
    }

    protected void postEnumLiteralExp(EnumLiteralExp exp, Arg arguments) {
    }

    @Override
    public Void visit(EnumLiteralExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preLiteralExp(exp, arguments);
        preEnumLiteralExp(exp, arguments);

        postOclExpression(exp, arguments);
        postLiteralExp(exp, arguments);
        postEnumLiteralExp(exp, arguments);

        return null;
    }

    protected void preFeatureCallExp(FeatureCallExp exp, Arg arguments) {
    }

    protected void postFeatureCallExp(FeatureCallExp exp, Arg arguments) {
    }

    protected void preIfExp(IfExp exp, Arg arguments) {
    }

    protected void postIfExp(IfExp exp, Arg arguments) {
    }

    @Override
    public Void visit(IfExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preIfExp(exp, arguments);

        if (exp.getCondition() != null) {
            exp.getCondition().accept(this, arguments);
        }
        if (exp.getThenExpression() != null) {
            exp.getThenExpression().accept(this, arguments);
        }
        if (exp.getElseExpression() != null) {
            exp.getElseExpression().accept(this, arguments);
        }

        postOclExpression(exp, arguments);
        postIfExp(exp, arguments);
        return null;
    }

    protected void preIntegerLiteralExp(IntegerLiteralExp exp, Arg arguments) {
    }

    protected void postIntegerLiteralExp(IntegerLiteralExp exp, Arg arguments) {
    }

    @Override
    public Void visit(IntegerLiteralExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preLiteralExp(exp, arguments);
        prePrimitiveLiteralExp(exp, arguments);
        preNumericLiteralExp(exp, arguments);
        preIntegerLiteralExp(exp, arguments);

        postOclExpression(exp, arguments);
        postLiteralExp(exp, arguments);
        postPrimitiveLiteralExp(exp, arguments);
        postNumericLiteralExp(exp, arguments);
        postIntegerLiteralExp(exp, arguments);
        return null;
    }

    protected void preInvalidLiteralExp(InvalidLiteralExp exp, Arg arguments) {
    }

    protected void postInvalidLiteralExp(InvalidLiteralExp exp, Arg arguments) {
        postLiteralExp(exp, arguments);
    }

    @Override
    public Void visit(InvalidLiteralExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preLiteralExp(exp, arguments);
        preInvalidLiteralExp(exp, arguments);
        
        postOclExpression(exp, arguments);
        postLiteralExp(exp, arguments);
        postInvalidLiteralExp(exp, arguments);
        return null;
    }

    protected void preIterateExp(IterateExp exp, Arg arguments) {
    }

    protected void postIterateExp(IterateExp exp, Arg arguments) {
    }

    @Override
    public Void visit(IterateExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preCallExp(exp, arguments);
        preLoopExp(exp, arguments);
        preIterateExp(exp, arguments);
        
        if (exp.getSource() != null) {
            exp.getSource().accept(this, arguments);
        }

        if (exp.getBody() != null) {
            exp.getBody().accept(this, arguments);
        }

        postOclExpression(exp, arguments);
        postCallExp(exp, arguments);
        postLoopExp(exp, arguments);
        postIterateExp(exp, arguments);
        return null;
    }

    protected void preIteratorExp(IteratorExp exp, Arg arguments) {
    }

    protected void postIteratorExp(IteratorExp exp, Arg arguments) {
    }

    @Override
    public Void visit(IteratorExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preCallExp(exp, arguments);
        preLoopExp(exp, arguments);
        preIteratorExp(exp, arguments);
        
        if (exp.getSource() != null) {
            exp.getSource().accept(this, arguments);
        }

        if (exp.getBody() != null) {
            exp.getBody().accept(this, arguments);
        }

        postOclExpression(exp, arguments);
        postCallExp(exp, arguments);
        postLoopExp(exp, arguments);
        postIteratorExp(exp, arguments);
        return null;
    }

    protected void preLetExp(LetExp exp, Arg arguments) {
    }

    protected void postVarLetExp(LetExp exp, Arg arguments) {
    }

    protected void postLetExp(LetExp exp, Arg arguments) {
    }

    @Override
    public Void visit(LetExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preLetExp(exp, arguments);

        if (exp.getVariable() != null) {
            exp.getVariable().accept(this, arguments);
        }

        postVarLetExp(exp, arguments);

        if (exp.getIn() != null) {
            exp.getIn().accept(this, arguments);
        }

        postOclExpression(exp, arguments);
        postLetExp(exp, arguments);
        return null;
    }

    protected void preLiteralExp(LiteralExp exp, Arg arguments) {
    }

    protected void postLiteralExp(LiteralExp exp, Arg arguments) {
    }

    protected void preLoopExp(LoopExp exp, Arg arguments) {
    }

    protected void postLoopExp(LoopExp exp, Arg arguments) {
    }

    protected void preMessageExp(MessageExp exp, Arg arguments) {
    }

    protected void postMessageExp(MessageExp exp, Arg arguments) {
    }

    @Override
    public Void visit(MessageExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preMessageExp(exp, arguments);
//        if (exp.getTarget() != null) {
//            exp.getTarget().accept(this, arguments);
//        }
//        for (OclExpression arg : exp.getArguments()) {
//            assert arg != null;
//            arg.accept(this, arguments);
//        }
        postOclExpression(exp, arguments);
        postMessageExp(exp, arguments);
        return null;
    }

    protected void preNavigationCallExp(NavigationCallExp exp, Arg arguments) {
    }

    protected void postNavigationCallExp(NavigationCallExp exp, Arg arguments) {
    }

    protected void preNullLiteralExp(NullLiteralExp exp, Arg arguments) {
    }

    protected void postNullLiteralExp(NullLiteralExp exp, Arg arguments) {
    }

    @Override
    public Void visit(NullLiteralExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preLiteralExp(exp, arguments);
        preNullLiteralExp(exp, arguments);
        
        postOclExpression(exp, arguments);
        postLiteralExp(exp, arguments);
        postNullLiteralExp(exp, arguments);
        return null;
    }

    protected void preNumericLiteralExp(NumericLiteralExp exp, Arg arguments) {
    }

    protected void postNumericLiteralExp(NumericLiteralExp exp, Arg arguments) {
    }

    protected void preOclExpression(OclExpression exp, Arg arguments) {
    }

    protected void postOclExpression(OclExpression exp, Arg arguments) {
    }

    protected void preOperationCallExp(OperationCallExp exp, Arg arguments) {
    }

    protected void postSourceOperationCallExp(OperationCallExp exp, Arg arguments) {
    }

    protected void postOperationCallExp(OperationCallExp exp, Arg arguments) {
    }

    @Override
    public Void visit(OperationCallExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preCallExp(exp, arguments);
        preFeatureCallExp(exp, arguments);
        preOperationCallExp(exp, arguments);

        exp.getSource().accept(this, arguments);

        postSourceOperationCallExp(exp, arguments);

        for (OclExpression arg : exp.getArguments()) {
            assert arg != null;
            arg.accept(this, arguments);
        }

        postOclExpression(exp, arguments);
        postCallExp(exp, arguments);
        postFeatureCallExp(exp, arguments);
        postOperationCallExp(exp, arguments);
        return null;
    }

    protected void prePrimitiveLiteralExp(PrimitiveLiteralExp exp, Arg arguments) {
    }

    protected void postPrimitiveLiteralExp(PrimitiveLiteralExp exp, Arg arguments) {
    }

    protected void preRealLiteralExp(RealLiteralExp exp, Arg arguments) {
    }

    protected void postRealLiteralExp(RealLiteralExp exp, Arg arguments) {
    }

    @Override
    public Void visit(RealLiteralExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preLiteralExp(exp, arguments);
        prePrimitiveLiteralExp(exp, arguments);
        preNumericLiteralExp(exp, arguments);
        preRealLiteralExp(exp, arguments);

        postOclExpression(exp, arguments);
        postLiteralExp(exp, arguments);
        postPrimitiveLiteralExp(exp, arguments);
        postNumericLiteralExp(exp, arguments);
        postRealLiteralExp(exp, arguments);

        return null;
    }

    protected void preStateExp(StateExp exp, Arg arguments) {
    }

    protected void postStateExp(StateExp exp, Arg arguments) {
    }

    @Override
    public Void visit(StateExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preStateExp(exp, arguments);

        postOclExpression(exp, arguments);
        postStateExp(exp, arguments);
        return null;
    }

    protected void preStringLiteralExp(StringLiteralExp exp, Arg arguments) {
    }

    protected void postStringLiteralExp(StringLiteralExp exp, Arg arguments) {
    }

    @Override
    public Void visit(StringLiteralExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preLiteralExp(exp, arguments);
        prePrimitiveLiteralExp(exp, arguments);
        preStringLiteralExp(exp, arguments);

        postOclExpression(exp, arguments);
        postLiteralExp(exp, arguments);
        postPrimitiveLiteralExp(exp, arguments);
        postStringLiteralExp(exp, arguments);

        return null;
    }

    protected void preTupleAttributeCallExp(TupleAttributeCallExp exp, Arg arguments) {
    }

    protected void postTupleAttributeCallExp(TupleAttributeCallExp exp, Arg arguments) {
    }

    @Override
    public Void visit(TupleAttributeCallExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preCallExp(exp, arguments);
        preTupleAttributeCallExp(exp, arguments);

        exp.getSource().accept(this, arguments);

        postOclExpression(exp, arguments);
        postCallExp(exp, arguments);
        postTupleAttributeCallExp(exp, arguments);
        return null;
    }

    protected void preTupleLiteralExp(TupleLiteralExp exp, Arg arguments) {
    }

    protected void postTupleLiteralExp(TupleLiteralExp exp, Arg arguments) {
    }

    @Override
    public Void visit(TupleLiteralExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preLiteralExp(exp, arguments);
        preTupleLiteralExp(exp, arguments);

        for (TupleLiteralPart tupleLiteralPart : exp.getParts()) {
            tupleLiteralPart.accept(this, arguments);
        }

        postOclExpression(exp, arguments);
        postLiteralExp(exp, arguments);
        postTupleLiteralExp(exp, arguments);

        return null;
    }

    protected void preTypeExp(TypeExp exp, Arg arguments) {
    }

    protected void postTypeExp(TypeExp exp, Arg arguments) {
    }

    @Override
    public Void visit(TypeExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preTypeExp(exp, arguments);

        postOclExpression(exp, arguments);
        postTypeExp(exp, arguments);

        return null;
    }

    protected void preUnlimitedNaturalExp(UnlimitedNaturalExp exp, Arg arguments) {
    }

    protected void postUnlimitedNaturalExp(UnlimitedNaturalExp exp, Arg arguments) {
    }

    @Override
    public Void visit(UnlimitedNaturalExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preLiteralExp(exp, arguments);
        prePrimitiveLiteralExp(exp, arguments);
        preNumericLiteralExp(exp, arguments);
        preUnlimitedNaturalExp(exp, arguments);

        postOclExpression(exp, arguments);
        postLiteralExp(exp, arguments);
        postPrimitiveLiteralExp(exp, arguments);
        postNumericLiteralExp(exp, arguments);
        postUnlimitedNaturalExp(exp, arguments);

        return null;
    }

    protected void preVariableExp(VariableExp exp, Arg arguments) {
    }

    protected void postVariableExp(VariableExp exp, Arg arguments) {
    }

    @Override
    public Void visit(VariableExp exp, Arg arguments) {
        preOclExpression(exp, arguments);
        preVariableExp(exp, arguments);

        postOclExpression(exp, arguments);
        postVariableExp(exp, arguments);

        return null;
    }

    protected void preVariable(Variable var, Arg arguments) {
    }

    protected void postVariable(Variable var, Arg arguments) {
    }

    @Override
    public Void visit(Variable var, Arg arguments) {
        preVariable(var, arguments);
        if (var.getInitExpression() != null) {
            var.getInitExpression().accept(this, arguments);
        }
        postVariable(var, arguments);
        return null;
    }

    protected void preCollectionLiteralPart(CollectionLiteralPart exp, Arg arguments) {
    }

    protected void postCollectionLiteralPart(CollectionLiteralPart exp, Arg arguments) {
    }

    protected void preCollectionRange(CollectionRange exp, Arg arguments) {
    }

    protected void postCollectionRange(CollectionRange exp, Arg arguments) {
    }

    @Override
    public Void visit(CollectionRange obj, Arg argument) {
        preCollectionLiteralPart(obj, argument);
        preCollectionRange(obj, argument);

        obj.getFirst().accept(this, argument);

        obj.getLast().accept(this, argument);

        postCollectionLiteralPart(obj, argument);
        postCollectionRange(obj, argument);

        return null;
    }

    protected void preCollectionItem(CollectionItem exp, Arg arguments) {
    }

    protected void postCollectionItem(CollectionItem exp, Arg arguments) {
    }

    @Override
    public Void visit(CollectionItem obj, Arg argument) {
        preCollectionLiteralPart(obj, argument);
        preCollectionItem(obj, argument);

        obj.getItem().accept(this, argument);

        postCollectionLiteralPart(obj, argument);
        postCollectionItem(obj, argument);
        return null;
    }

    protected void preTupleLiteralPart(TupleLiteralPart exp, Arg arguments) {
    }

    protected void postTupleLiteralPart(TupleLiteralPart exp, Arg arguments) {
    }

    @Override
    public Void visit(TupleLiteralPart tupleLiteralPart, Arg argument) {
        preTupleLiteralPart(tupleLiteralPart, argument);

        tupleLiteralPart.getAttribute().getInitExpression().accept(this, argument);

        postTupleLiteralPart(tupleLiteralPart, argument);

        return null;
    }
}
