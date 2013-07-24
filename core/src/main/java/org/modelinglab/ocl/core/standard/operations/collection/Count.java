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
 * The number of times that object occurs in the collection self.
 * <p><code>
 * count(object : T) : Integer<br/>
 * post: result = self->iterate( elem; acc : Integer = 0 |<br/>
 * if elem = object then acc + 1 else acc endif)<br/>
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Count extends Operation {

    private static final long serialVersionUID = 1L;

    public Count(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        Parameter object = new Parameter();
        object.setName("object");
        object.setType(tType);
        addOwnedParameter(object);

        setName("count");
        setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new Count(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static Count createTemplateOperation() {
        return new Count(null, TemplateParameterType.getGenericCollectionElement());
    }
}
