/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;

/**
 * SequenceType is a collection type that describes a list of elements where each element may occur
 * multiple times in the sequence. The elements are ordered by their position in the sequence. Part 
 * of a SequenceType is the declaration of the type of its elements.
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class SequenceType extends CollectionType {
    private static final long serialVersionUID = 1L;
    
    private static SequenceType genericInstance = null;

    public SequenceType() {
    }

    public SequenceType(Classifier elementType) {
        super(elementType);
    }
    
    public static SequenceType getGenericInstance() {
        if (genericInstance == null) {
            genericInstance = new SequenceType();
            genericInstance.setElementType(TemplateParameterType.getGenericCollectionElement());
        }
        return genericInstance;        
    }

    @Override
    public CollectionKind getCollectionKind() {
        return CollectionKind.SEQUENCE;
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
