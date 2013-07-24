/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.iterators.sequence;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.standard.iterators.exceptions.IllegalIteratorException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class Select extends IteratorExp {
    private static final long serialVersionUID = 1L;

    public Select(CollectionType sourceType, Classifier bodyType) throws IllegalIteratorException {
        setName("select");
        CollectionType resultType = new SequenceType();
        resultType.setElementType(sourceType.getElementType());
        setType(resultType);
        
        if (!bodyType.equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN))) {
            throw new IllegalIteratorException("select", sourceType, bodyType, 
                    "In 'select' iterators, body type must be Boolean");
        }
    }

    public Select(Select other) {
        super(other);
    }
    
    @Override
    public Select clone() {
        return new Select(this);
    }
    
}
