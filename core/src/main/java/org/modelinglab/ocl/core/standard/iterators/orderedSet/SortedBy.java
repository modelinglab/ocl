/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.iterators.orderedSet;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class SortedBy extends IteratorExp {
    private static final long serialVersionUID = 1L;

    public SortedBy(CollectionType sourceType, Classifier bodyType) {
        setName("sortedBy");
        CollectionType resultType = new OrderedSetType();
        resultType.setElementType(sourceType.getElementType());
        setType(resultType);
        
        //TODO: check if bodyType is orderable!
    }

    public SortedBy(SortedBy other) {
        super(other);
    }
    
    @Override
    public SortedBy clone() {
        return new SortedBy(this);
    }
    
}