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
 * Is self not the empty collection?
 * <p><code>
 * notEmpty() : Boolean<br/>
 * post: result = ( self->size() <> 0 )<br/>
 * </code></p>
 * null->notEmpty() returns 'false' in virtue of the implicit casting from null to Bag{}.<br/>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class NotEmpty extends Operation {

    private static final long serialVersionUID = 1L;

    public NotEmpty(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        setName("notEmpty");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new NotEmpty(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static NotEmpty createTemplateOperation() {
        return new NotEmpty(null, TemplateParameterType.getGenericCollectionElement());
    }
}
