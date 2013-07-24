/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.collection;

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
 * Is self the empty collection?
 * <p><code>
 * isEmpty() : Boolean<br/>
 * post: result = ( self->size() = 0 )<br/>
 * </code></p>
 * Note: null->isEmpty() returns 'true' in virtue of the implicit casting from null to Bag{}
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class IsEmpty extends Operation {

    private static final long serialVersionUID = 1L;

    public IsEmpty(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        setName("isEmpty");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new IsEmpty(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static IsEmpty createTemplateOperation() {
        return new IsEmpty(null, TemplateParameterType.getGenericCollectionElement());
    }
}
