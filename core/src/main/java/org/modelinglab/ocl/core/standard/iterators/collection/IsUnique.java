/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.iterators.collection;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class IsUnique extends IteratorExp {
    private static final long serialVersionUID = 1L;

    public IsUnique(CollectionType sourceType, Classifier bodyType) {
        setName("isUnique");
        setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
    }

    public IsUnique(IsUnique other) {
        super(other);
    }
    
    @Override
    public IsUnique clone() {
        return new IsUnique(this);
    }
}
