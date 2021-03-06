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
public final class Reject extends IteratorExp {
    private static final long serialVersionUID = 1L;

    public Reject(CollectionType sourceType, Classifier bodyType) throws IllegalIteratorException {
        setName("reject");
        CollectionType resultType = new SequenceType();
        resultType.setElementType(sourceType.getElementType());
        setType(resultType);
        
        if (!bodyType.equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN))) {
            throw new IllegalIteratorException("reject", sourceType, bodyType, 
                    "In 'reject' iterators, body type must be Boolean");
        }
    }

    public Reject(Reject other) {
        super(other);
    }
    
    @Override
    public Reject clone() {
        return new Reject(this);
    }
    
}