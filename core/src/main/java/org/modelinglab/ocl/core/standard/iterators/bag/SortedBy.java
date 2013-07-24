/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.iterators.bag;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.SequenceType;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class SortedBy extends IteratorExp {

    public SortedBy(CollectionType sourceType, Classifier bodyType) {
        setName("sortedBy");
        CollectionType resultType = new SequenceType();
        resultType.setElementType(sourceType.getElementType());
        setType(resultType);
    }

    public SortedBy(SortedBy other) {
        super(other);
    }
    
    @Override
    public SortedBy clone() {
        return new SortedBy(this);
    }
    
}