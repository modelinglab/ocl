/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.iterators.bag;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class CollectNested extends IteratorExp {
    private static final long serialVersionUID = 1L;

    public CollectNested(CollectionType sourceType, Classifier bodyType) {
        setName("collectNested");
        CollectionType resultType = new BagType();
        resultType.setElementType(bodyType);
        setType(resultType);
    }

    public CollectNested(CollectNested other) {
        super(other);
    }

    @Override
    public CollectionType getType() {
        return (CollectionType) super.getType();
    }
    
    @Override
    public CollectNested clone() {
        return new CollectNested(this);
    }
    
}