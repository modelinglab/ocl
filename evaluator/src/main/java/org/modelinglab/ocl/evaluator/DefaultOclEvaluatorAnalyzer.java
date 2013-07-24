/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.AssociationEndCallExp;
import org.modelinglab.ocl.core.ast.expressions.AttributeCallExp;
import org.modelinglab.ocl.core.ast.expressions.BooleanLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.CollectionLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.EnumLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.IfExp;
import org.modelinglab.ocl.core.ast.expressions.IntegerLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.InvalidLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.LetExp;
import org.modelinglab.ocl.core.ast.expressions.NullLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.expressions.RealLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.StringLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.TupleAttributeCallExp;
import org.modelinglab.ocl.core.ast.expressions.TupleLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.TypeExp;
import org.modelinglab.ocl.core.ast.expressions.UnlimitedNaturalExp;
import org.modelinglab.ocl.core.ast.expressions.VariableExp;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.EnumValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.NaturalValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.core.values.TupleValue;
import org.modelinglab.ocl.core.values.VoidValue;

/**
 *
 */
public class DefaultOclEvaluatorAnalyzer implements OclEvaluatorAnalyzer {

    @Override
    public void analyze(AssociationEndCallExp exp, OclValue<?> source, OclValue<?> result) {
    }

    @Override
    public void analyze(AttributeCallExp exp, OclValue<?> source, OclValue<?> result) {
    }

    @Override
    public void analyze(BooleanLiteralExp exp, BooleanValue result) {
    }

    @Override
    public void analyze(CollectionLiteralExp exp, OclValue<?> result) {
    }

    @Override
    public void analyze(EnumLiteralExp exp, EnumValue result) {
    }

    @Override
    public void analyze(IfExp exp, OclValue<?> condition, OclValue<?> thenVal, OclValue<?> elseVal) {
    }

    @Override
    public void analyze(IntegerLiteralExp exp, IntegerValue result) {
    }

    @Override
    public void analyze(InvalidLiteralExp exp, InvalidValue result) {
    }

    @Override
    public void analyze(IteratorExp exp, OclValue<?> source, OclValue<?> result) {
    }

    @Override
    public void analyze(LetExp exp, OclValue<?> initialVal, OclValue<?> result) {
    }

    @Override
    public void analyze(NullLiteralExp exp, VoidValue result) {
    }

    @Override
    public void analyze(OperationCallExp exp, OclValue<?> source, List<OclValue<?>> argVals, OclValue<?> result) {
    }

    @Override
    public void analyze(RealLiteralExp exp, RealValue result) {
    }

    @Override
    public void analyze(StringLiteralExp exp, StringValue result) {
    }

    @Override
    public void analyze(TupleAttributeCallExp exp, OclValue<?> source, OclValue<?> result) {
    }

    @Override
    public void analyze(TupleLiteralExp exp, TupleValue result) {
    }

    @Override
    public void analyze(TypeExp exp, ClassifierValue result) {
    }

    @Override
    public void analyze(UnlimitedNaturalExp exp, NaturalValue result) {
    }

    @Override
    public void analyze(VariableExp exp, OclValue<?> result) {
    }


}
