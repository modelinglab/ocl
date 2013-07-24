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
 * The number of elements in the collection self.
 * <p><code>
 * size() : Integer</br>
 * post: result = self->iterate(elem; acc : Integer = 0 | acc + 1)
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Size extends Operation {

    private static final long serialVersionUID = 1L;

    public Size(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        setName("size");
        setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new Size(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static Size createTemplateOperation() {
        return new Size(null, TemplateParameterType.getGenericCollectionElement());
    }
}
