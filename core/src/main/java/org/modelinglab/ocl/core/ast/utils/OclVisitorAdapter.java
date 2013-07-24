/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.core.ast.types.InvalidType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.DataType;
import org.modelinglab.ocl.core.ast.types.MessageType;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.TupleType;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
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
import org.modelinglab.ocl.core.ast.expressions.EnumLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.AssociationEndCallExp;
import org.modelinglab.ocl.core.ast.expressions.StateExp;
import org.modelinglab.ocl.core.ast.expressions.IterateExp;
import org.modelinglab.ocl.core.ast.expressions.NavigationCallExp;
import org.modelinglab.ocl.core.ast.expressions.CollectionLiteralPart;
import org.modelinglab.ocl.core.ast.expressions.MessageExp;
import org.modelinglab.ocl.core.ast.expressions.IfExp;
import org.modelinglab.ocl.core.ast.expressions.LoopExp;
import org.modelinglab.ocl.core.ast.expressions.NumericLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.NullLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.CollectionRange;
import org.modelinglab.ocl.core.ast.expressions.BooleanLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.AttributeCallExp;
import org.modelinglab.ocl.core.ast.expressions.IntegerLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.CollectionItem;
import org.modelinglab.ocl.core.ast.expressions.TupleLiteralPart;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.AssociationEnd;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.Namespace;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.ast.Comment;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.UmlEnum;

/**
 *
 * @param <Result> 
 * @param <Args> 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclVisitorAdapter<Result, Args> implements OclVisitor<Result, Args> {

    public Result defaultCase(Visitable obj, Args arguments) {
        return null;
    }

    @Override
    public Result visit(AssociationEnd o, Args arguments) {
        return defaultCase(o, arguments);
    }

    @Override
    public Result visit(Attribute o, Args arguments) {
        return defaultCase(o, arguments);
    }

    @Override
    public Result visit(Comment o, Args arguments) {
        return defaultCase(o, arguments);
    }

    @Override
    public Result visit(Operation o, Args arguments) {
        return defaultCase(o, arguments);
    }

    @Override
    public Result visit(Parameter o, Args arguments) {
        return defaultCase(o, arguments);
    }

    @Override
    public Result visit(UmlEnum o, Args arguments) {
        return visit((UmlClass) o, arguments);
    }

    @Override
    public Result visit(UmlEnumLiteral o, Args arguments) {
        return defaultCase(o, arguments);
    }
    
    @Override
    public Result visit(Namespace o, Args argument) {
        return defaultCase(o, argument);
    }

    public Result visit(CollectionLiteralPart obj, Args arguments) {
        return defaultCase(obj, arguments);
    }

    @Override
    public Result visit(CollectionRange obj, Args arguments) {
        return visit((CollectionLiteralPart) obj, arguments);
    }

    @Override
    public Result visit(CollectionItem obj, Args arguments) {
        return visit((CollectionLiteralPart) obj, arguments);
    }

    @Override
    public Result visit(AssociationEndCallExp exp, Args arguments) {
        return visit((NavigationCallExp) exp, arguments);
    }

    @Override
    public Result visit(AttributeCallExp exp, Args arguments) {
        return visit((FeatureCallExp) exp, arguments);
    }

    @Override
    public Result visit(BooleanLiteralExp exp, Args arguments) {
        return visit((PrimitiveLiteralExp) exp, arguments);
    }

    public Result visit(CallExp exp, Args arguments) {
        return visit((OclExpression) exp, arguments);
    }

    @Override
    public Result visit(CollectionLiteralExp exp, Args arguments) {
        return visit((LiteralExp) exp, arguments);
    }

    @Override
    public Result visit(EnumLiteralExp exp, Args arguments) {
        return visit((LiteralExp) exp, arguments);
    }

    public Result visit(FeatureCallExp exp, Args arguments) {
        return visit((CallExp) exp, arguments);
    }

    @Override
    public Result visit(IfExp exp, Args arguments) {
        return visit((OclExpression) exp, arguments);
    }

    @Override
    public Result visit(IntegerLiteralExp exp, Args arguments) {
        return visit((NumericLiteralExp) exp, arguments);
    }

    @Override
    public Result visit(InvalidLiteralExp exp, Args arguments) {
        return visit((LiteralExp) exp, arguments);
    }

    @Override
    public Result visit(IterateExp exp, Args arguments) {
        return visit((LoopExp) exp, arguments);
    }

    @Override
    public Result visit(IteratorExp exp, Args arguments) {
        return visit((LoopExp) exp, arguments);
    }

    @Override
    public Result visit(LetExp exp, Args arguments) {
        return visit((OclExpression) exp, arguments);
    }

    public Result visit(LiteralExp exp, Args arguments) {
        return visit((OclExpression) exp, arguments);
    }

    public Result visit(LoopExp exp, Args arguments) {
        return visit((OclExpression) exp, arguments);
    }

    @Override
    public Result visit(MessageExp exp, Args arguments) {
        return visit((OclExpression) exp, arguments);
    }

    public Result visit(NavigationCallExp exp, Args arguments) {
        return visit((FeatureCallExp) exp, arguments);
    }

    @Override
    public Result visit(NullLiteralExp exp, Args arguments) {
        return visit((LiteralExp) exp, arguments);
    }

    public Result visit(NumericLiteralExp exp, Args arguments) {
        return visit((PrimitiveLiteralExp) exp, arguments);
    }

    public Result visit(OclExpression exp, Args arguments) {
        return defaultCase(exp, arguments);
    }

    @Override
    public Result visit(OperationCallExp exp, Args arguments) {
        return visit((FeatureCallExp) exp, arguments);
    }

    public Result visit(PrimitiveLiteralExp exp, Args arguments) {
        return visit((LiteralExp) exp, arguments);
    }

    @Override
    public Result visit(RealLiteralExp exp, Args arguments) {
        return visit((NumericLiteralExp) exp, arguments);
    }

    @Override
    public Result visit(StateExp exp, Args arguments) {
        return visit((OclExpression) exp, arguments);
    }

    @Override
    public Result visit(StringLiteralExp exp, Args arguments) {
        return visit((PrimitiveLiteralExp) exp, arguments);
    }

    @Override
    public Result visit(TupleAttributeCallExp exp, Args argument) {
        return visit((CallExp) exp, argument);
    }

    @Override
    public Result visit(TupleLiteralExp exp, Args arguments) {
        return visit((LiteralExp) exp, arguments);
    }

    @Override
    public Result visit(TypeExp exp, Args arguments) {
        return visit((OclExpression) exp, arguments);
    }

    @Override
    public Result visit(UnlimitedNaturalExp exp, Args arguments) {
        return visit((NumericLiteralExp) exp, arguments);
    }

    @Override
    public Result visit(VariableExp exp, Args arguments) {
        return visit((OclExpression) exp, arguments);
    }

    @Override
    public Result visit(Variable var, Args argument) {
        return defaultCase(var, argument);
    }
    
    @Override
    public Result visit(AnyType type, Args arguments) {
        return visit((Classifier) type, arguments);
    }

    @Override
    public Result visit(BagType type, Args arguments) {
        return visit((CollectionType) type, arguments);
    }

    public Result visit(Classifier type, Args arguments) {
        return defaultCase(type, arguments);
    }

    @Override
    public Result visit(CollectionType type, Args arguments) {
        return visit((DataType) type, arguments);
    }

    public Result visit(DataType type, Args arguments) {
        return visit((Classifier) type, arguments);
    }

    @Override
    public Result visit(InvalidType type, Args arguments) {
        return visit((Classifier) type, arguments);
    }

    @Override
    public Result visit(MessageType type, Args arguments) {
        return visit((Classifier) type, arguments);
    }

    @Override
    public Result visit(OrderedSetType type, Args arguments) {
        return visit((CollectionType) type, arguments);
    }

    @Override
    public Result visit(PrimitiveType type, Args arguments) {
        return visit((DataType) type, arguments);
    }

    @Override
    public Result visit(SequenceType type, Args arguments) {
        return visit((CollectionType) type, arguments);
    }

    @Override
    public Result visit(SetType type, Args arguments) {
        return visit((CollectionType) type, arguments);
    }

    @Override
    public Result visit(TemplateParameterType type, Args arguments) {
        return visit((Classifier) type, arguments);
    }

    @Override
    public Result visit(TupleType type, Args arguments) {
        return visit((DataType) type, arguments);
    }

    @Override
    public Result visit(UmlClass type, Args arguments) {
        return visit((Classifier) type, arguments);
    }

    @Override
    public Result visit(VoidType type, Args arguments) {
        return visit((Classifier) type, arguments);
    }

    @Override
    public Result visit(ClassifierType classifierType, Args arguments) {
        return visit((Classifier) classifierType, arguments);
    }

    @Override
    public Result visit(TupleLiteralPart tupleLiteralPart, Args argument) {
        return defaultCase(tupleLiteralPart, argument);
    }
}
