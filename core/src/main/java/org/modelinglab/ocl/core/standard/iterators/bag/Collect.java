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
public final class Collect extends IteratorExp {
    private static final long serialVersionUID = 1L;
    
    public Collect(CollectionType sourceType, Classifier bodyType) {
        setName("collect");
        CollectionType resultType = new BagType();
        
        Classifier tResultType = bodyType;
        while (tResultType instanceof CollectionType) {
            tResultType = ((CollectionType) tResultType).getElementType();
        }
        
        resultType.setElementType(tResultType);
        setType(resultType);
    }
    
    protected Collect(Collect other) {
        super(other);
    }

    @Override
    public CollectionType getType() {
        return (CollectionType) super.getType();
    }

    @Override
    public Collect clone() {
        return new Collect(this);
    }
}
