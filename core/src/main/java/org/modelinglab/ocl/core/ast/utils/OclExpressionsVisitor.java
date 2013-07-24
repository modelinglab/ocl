/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.expressions.VariableExp;
import org.modelinglab.ocl.core.ast.expressions.StringLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.expressions.InvalidLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.UnlimitedNaturalExp;
import org.modelinglab.ocl.core.ast.expressions.TypeExp;
import org.modelinglab.ocl.core.ast.expressions.EnumLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.AssociationEndCallExp;
import org.modelinglab.ocl.core.ast.expressions.StateExp;
import org.modelinglab.ocl.core.ast.expressions.IterateExp;
import org.modelinglab.ocl.core.ast.expressions.TupleLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.MessageExp;
import org.modelinglab.ocl.core.ast.expressions.IfExp;
import org.modelinglab.ocl.core.ast.expressions.CollectionLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.expressions.TupleAttributeCallExp;
import org.modelinglab.ocl.core.ast.expressions.NullLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.BooleanLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.LetExp;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.AttributeCallExp;
import org.modelinglab.ocl.core.ast.expressions.RealLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.IntegerLiteralExp;

/**
 *
 * @param <Result> the return type of this visitor's methods. Use Void for visitors that do not need to return results.
 * @param <Arg> the type of the additional parameter to this visitor's methods. Use Void for visitors that do not need an additional parameter.
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface OclExpressionsVisitor<Result, Arg> {
    
    Result visit(AssociationEndCallExp exp, Arg argument);
    
    Result visit(AttributeCallExp exp, Arg argument);
    
    Result visit(BooleanLiteralExp exp, Arg argument);
    
    Result visit(CollectionLiteralExp exp, Arg argument);
   
    Result visit(EnumLiteralExp exp, Arg argument);
    
    Result visit(IfExp exp, Arg argument);
    
    Result visit(IntegerLiteralExp exp, Arg argument);
    
    Result visit(InvalidLiteralExp exp, Arg argument);
    
    Result visit(IterateExp exp, Arg argument);
    
    Result visit(IteratorExp exp, Arg argument);
    
    Result visit(LetExp exp, Arg argument);
    
    Result visit(MessageExp exp, Arg argument);
    
    Result visit(NullLiteralExp exp, Arg argument);
    
    Result visit(OperationCallExp exp, Arg argument);
    
    Result visit(RealLiteralExp exp, Arg argument);
    
    Result visit(StateExp exp, Arg argument);
    
    Result visit(StringLiteralExp exp, Arg argument);
    
    Result visit(TupleAttributeCallExp exp, Arg argument);
    
    Result visit(TupleLiteralExp exp, Arg argument);
    
    Result visit(TypeExp exp, Arg argument);
    
    Result visit(UnlimitedNaturalExp exp, Arg argument);
    
    Result visit(VariableExp exp, Arg argument);
    
    Result visit(Variable var, Arg argument);
}
