/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import org.modelinglab.ocl.core.ast.types.CollectionType.CollectionKind;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class SetType extends CollectionType {
    private static final long serialVersionUID = 1L;
    
    private static SetType genericInstance = null;

    public SetType() {
    }

    public SetType(Classifier elementType) {
        super(elementType);
    }
    
    public static SetType getGenericInstance() {
        if (genericInstance == null) {
            genericInstance = new SetType();
            genericInstance.setElementType(TemplateParameterType.getGenericCollectionElement());
        }
        return genericInstance;        
    }

    @Override
    public CollectionKind getCollectionKind() {
        return CollectionKind.SET;
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
