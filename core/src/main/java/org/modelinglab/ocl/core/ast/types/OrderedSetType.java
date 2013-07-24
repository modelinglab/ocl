/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;

/**
 * OrderedSetType is a collection type that describes a set of elements where each distinct element 
 * occurs only once in the set. The elements are ordered by their position in the sequence. Part of 
 * an OrderedSetType is the declaration of the type of its elements.
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class OrderedSetType extends CollectionType {
    private static final long serialVersionUID = 1L;
    
    private static OrderedSetType genericInstance = null;

    public OrderedSetType() {
    }

    public OrderedSetType(Classifier elementType) {
        super(elementType);
    }
    
    protected OrderedSetType(OrderedSetType other) {
        super(other);
    }
    
    public static OrderedSetType getGenericInstance() {
        if (genericInstance == null) {
            genericInstance = new OrderedSetType();
            genericInstance.setElementType(TemplateParameterType.getGenericCollectionElement());
        }
        return genericInstance;        
    }

    @Override
    public CollectionKind getCollectionKind() {
        return CollectionKind.ORDERED_SET;
    }

    @Override
    public OrderedSetType clone() {
        return new OrderedSetType(this);
    }

    @Override
    public <Result, Arg> Result accept(ClassifierVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
