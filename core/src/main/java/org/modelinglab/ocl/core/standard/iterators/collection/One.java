/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.iterators.collection;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.standard.iterators.exceptions.IllegalIteratorException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class One extends IteratorExp {
    private static final long serialVersionUID = 1L;

    public One(CollectionType sourceType, Classifier bodyType) throws IllegalIteratorException {
        setName("one");
        setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        if (!bodyType.equals(getType())) {
            throw new IllegalIteratorException("one", sourceType, bodyType, 
                    "In 'one' iterators, body type must be Boolean");
        }
    }

    public One(One other) {
        super(other);
    }
    
    @Override
    public One clone() {
        return new One(this);
    }
}
