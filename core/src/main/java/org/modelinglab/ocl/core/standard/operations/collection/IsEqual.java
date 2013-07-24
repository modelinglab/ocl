/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.collection;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * True if c is a collection of the same kind as self and contains the same elements in the same
 * quantities and in the same order, in the case of an ordered collection type.
 * <p><code>= (c : Collection(T)) : Boolean</code></p>


 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class IsEqual extends Operation {

    private static final long serialVersionUID = 1L;

    public IsEqual(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        Parameter c = new Parameter();
        c.setName("c");
        c.setType(ct);
        addOwnedParameter(c);

        setName("=");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new IsEqual(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static IsEqual createTemplateOperation() {
        return new IsEqual(null, TemplateParameterType.getGenericCollectionElement());
    }
}
