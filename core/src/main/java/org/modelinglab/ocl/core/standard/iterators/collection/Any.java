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
public class Any extends IteratorExp {

    public Any(CollectionType sourceType, Classifier bodyType) throws IllegalIteratorException {
        setName("any");
        setType(sourceType.getElementType());
        if (!bodyType.equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN))) {
            throw new IllegalIteratorException("any", sourceType, bodyType, 
                    "In 'any' iterators, body type must be Boolean");
        }
    }

    public Any(Any other) {
        super(other);
    }
    
    @Override
    public Any clone() {
        return new Any(this);
    }
}
