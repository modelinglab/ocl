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
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlEnum;

/**
 *
 * @param <Result> 
 * @param <Arg> 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ClassifierVisitorAdaptor<Result, Arg> implements ClassifierVisitor<Result, Arg> {

    @Override
    public Result visit(AnyType anyType, Arg argument) {
        return visit((Classifier) anyType, argument);
    }

    @Override
    public Result visit(BagType bagType, Arg argument) {
        return visit((CollectionType) bagType, argument);
    }

    public Result visit(Classifier classifier, Arg argument) {
        return null;
    }

    @Override
    public Result visit(CollectionType collectionType, Arg argument) {
        return visit((DataType) collectionType, argument);
    }

    public Result visit(DataType dataType, Arg argument) {
        return visit((Classifier) dataType, argument);
    }

    @Override
    public Result visit(InvalidType invalidType, Arg argument) {
        return visit((Classifier) invalidType, argument);
    }

    @Override
    public Result visit(MessageType messageType, Arg argument) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Result visit(OrderedSetType orderedSetType, Arg argument) {
        return visit((CollectionType) orderedSetType, argument);
    }

    @Override
    public Result visit(PrimitiveType primitiveType, Arg argument) {
        return visit((DataType) primitiveType, argument);
    }

    @Override
    public Result visit(SequenceType sequenceType, Arg argument) {
        return visit((CollectionType) sequenceType, argument);
    }

    @Override
    public Result visit(TemplateParameterType templateParameterType, Arg argument) {
        return visit((Classifier) templateParameterType, argument);
    }

    @Override
    public Result visit(TupleType tupleType, Arg argument) {
        return visit((DataType) tupleType, argument);
    }

    @Override
    public Result visit(SetType setType, Arg argument) {
        return visit((CollectionType) setType, argument);
    }

    @Override
    public Result visit(UmlClass umlClass, Arg argument) {
        return visit((Classifier) umlClass, argument);
    }

    @Override
    public Result visit(UmlEnum umlEnum, Arg argument) {
        return visit((UmlClass) umlEnum, argument);
    }

    @Override
    public Result visit(VoidType voidType, Arg argument) {
        return visit((Classifier) voidType, argument);
    }

    @Override
    public Result visit(ClassifierType classifierType, Arg argument) {
        return visit((Classifier) classifierType, argument);
    }
    
}
