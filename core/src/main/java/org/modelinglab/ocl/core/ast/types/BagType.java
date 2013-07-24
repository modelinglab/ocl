/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import org.modelinglab.ocl.core.ast.types.CollectionType.CollectionKind;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;

/**
 * BagType is a collection type that describes a multiset of elements where each element may occur 
 * multiple times in the bag. The elements are unordered. Part of a BagType is the declaration of 
 * the type of its elements.
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class BagType extends CollectionType {

    private static final long serialVersionUID = 1L;
    private static BagType genericInstance = null;

    public BagType() {
    }

    public BagType(Classifier elementType) {
        super(elementType);
    }

    public static BagType getGenericInstance() {
        if (genericInstance == null) {
            genericInstance = new BagType();
            genericInstance.setElementType(TemplateParameterType.getGenericCollectionElement());
        }
        return genericInstance;
    }

    @Override
    public CollectionKind getCollectionKind() {
        return CollectionKind.BAG;
    }

    @Override
    public BagType clone() {
        return new BagType(this);
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
