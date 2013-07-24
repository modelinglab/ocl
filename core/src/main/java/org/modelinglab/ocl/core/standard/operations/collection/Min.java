/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * The element with the minimum value of all elements in self. Elements must be of a type supporting
 * the min operation.<br/>
 * The min operation - supported by the elements - must take one parameter of type T and be both
 * associative and commutative. Integer and Real fulfill this condition.<br/>
 * <p><code>
 * min() : T<br/>
 * post: result = self->iterate( elem; acc : T = self.first() | acc.min(elem) )<br/>
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Min extends Operation {

    private static final long serialVersionUID = 1L;

    public Min(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        setName("min");
        setType(tType);
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new Min(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static Min createTemplateOperation() {
        return new Min(null, TemplateParameterType.getGenericCollectionElement());
    }
}
