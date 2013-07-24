/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.InvalidType;
import org.modelinglab.ocl.core.ast.types.MessageType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TupleType;
import org.modelinglab.ocl.core.ast.types.VoidType;

/**
 * 
 * @param <Result> the return type of this visitor's methods. Use Void for visitors that do not need to return results.
 * @param <Arg> the type of the additional parameter to this visitor's methods. Use Void for visitors that do not need an additional parameter.
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface ClassifierVisitor<Result, Arg> {
    
    public Result visit(AnyType anyType, Arg argument);
    
    public Result visit(BagType bagType, Arg argument);
    
    public Result visit(CollectionType collectionType, Arg argument);
    
    public Result visit(InvalidType invalidType, Arg argument);
    
    public Result visit(MessageType messageType, Arg argument);
    
    public Result visit(OrderedSetType orderedSetType, Arg argument);
    
    public Result visit(PrimitiveType primitiveType, Arg argument);
    
    public Result visit(SequenceType sequenceType, Arg argument);
    
    public Result visit(TemplateParameterType templateParameterType, Arg argument);
    
    public Result visit(TupleType tupleType, Arg argument);
    
    public Result visit(SetType setType, Arg argument);
    
    public Result visit(UmlClass umlClass, Arg argument);
    
    public Result visit(UmlEnum umlEnum, Arg argument);
    
    public Result visit(VoidType voidType, Arg argument);
    
    public Result visit(ClassifierType classifierType, Arg argument);
    
}
