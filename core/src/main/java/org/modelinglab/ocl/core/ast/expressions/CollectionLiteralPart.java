/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.TypedElement;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.CollectionLiteralPartVisitor;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class CollectionLiteralPart extends TypedElement implements Cloneable {

    public CollectionLiteralPart() {
    }

    protected CollectionLiteralPart(CollectionLiteralPart other) {
        super(other);
    }

    @Override
    public abstract Classifier getType();
    
    @Override
    public abstract CollectionLiteralPart clone();
    
    public abstract <Result, Arg> Result accept(CollectionLiteralPartVisitor<Result, Arg> visitor, Arg arguments);
    
    @Override
    @Deprecated
    public void setType(Classifier type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public abstract String getName();

    @Override
    @Deprecated
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
