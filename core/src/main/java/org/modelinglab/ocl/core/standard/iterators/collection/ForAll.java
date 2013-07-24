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
public final class ForAll extends IteratorExp {
    private static final long serialVersionUID = 1L;

    public ForAll(CollectionType sourceType, Classifier bodyType) throws IllegalIteratorException {
        setName("forAll");
        setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        if (!bodyType.equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN))) {
            throw new IllegalIteratorException("forAll", sourceType, bodyType, 
                    "In 'forAll' iterators, body type must be Boolean");
        }
    }

    public ForAll(ForAll other) {
        super(other);
    }
    
    @Override
    public ForAll clone() {
        return new ForAll(this);
    }
}
