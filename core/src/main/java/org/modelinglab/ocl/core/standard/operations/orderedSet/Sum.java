/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.orderedSet;

import javax.annotation.concurrent.Immutable;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;

/**
 * Redefines the Collection operation to remove the requirement for the + operation to be associative and/or
 * commutative, since the order of evaluation is well-defined by the iteration over an ordered collection.
 */
@Immutable
public class Sum extends Operation {

    private static final long serialVersionUID = 1L;

    public Sum(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new OrderedSetType();
        ct.setElementType(tType);

        setName("sum");
        setType(tType);
        setSource(ct);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new Sum(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static Sum createTemplateOperation() {
        return new Sum(null, TemplateParameterType.getGenericCollectionElement());
    }
}
