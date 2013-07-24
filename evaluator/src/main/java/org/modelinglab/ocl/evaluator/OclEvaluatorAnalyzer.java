/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
public interface OclEvaluatorAnalyzer {

    void analyze(@Nonnull AssociationEndCallExp exp, @Nonnull OclValue<?> source, @Nonnull OclValue<?> result);
    
    void analyze(@Nonnull AttributeCallExp exp, @Nonnull OclValue<?> source, @Nonnull OclValue<?> result);
    
    void analyze(@Nonnull BooleanLiteralExp exp, @Nonnull BooleanValue result);
    
    void analyze(@Nonnull CollectionLiteralExp exp, @Nonnull OclValue<?> result);
   
    void analyze(@Nonnull EnumLiteralExp exp, @Nonnull EnumValue result);
    
    void analyze(@Nonnull IfExp exp, @Nonnull OclValue<?> condition, @Nullable OclValue<?> thenVal, @Nullable OclValue<?> elseVal);
    
    void analyze(@Nonnull IntegerLiteralExp exp, @Nonnull IntegerValue result);
    
    void analyze(@Nonnull InvalidLiteralExp exp, @Nonnull InvalidValue result);
    
    void analyze(@Nonnull IteratorExp exp, @Nonnull OclValue<?> source, @Nonnull OclValue<?> result);
    
    void analyze(@Nonnull LetExp exp, @Nonnull OclValue<?> initialVal, @Nonnull OclValue<?> result);
    
    void analyze(@Nonnull NullLiteralExp exp, @Nonnull VoidValue result);
    
    void analyze(@Nonnull OperationCallExp exp, @Nonnull OclValue<?> source, @Nonnull List<OclValue<?>> argVals, @Nonnull OclValue<?> result);
    
    void analyze(@Nonnull RealLiteralExp exp, @Nonnull RealValue result);
    
    void analyze(@Nonnull StringLiteralExp exp, @Nonnull StringValue result);
    
    void analyze(@Nonnull TupleAttributeCallExp exp, @Nonnull OclValue<?> source, @Nonnull OclValue<?> result);
    
    void analyze(@Nonnull TupleLiteralExp exp, @Nonnull TupleValue result);
    
    void analyze(@Nonnull TypeExp exp, @Nonnull ClassifierValue result);
    
    void analyze(@Nonnull UnlimitedNaturalExp exp, @Nonnull NaturalValue result);
    
    void analyze(@Nonnull VariableExp exp, @Nonnull OclValue<?> result);
    
}
