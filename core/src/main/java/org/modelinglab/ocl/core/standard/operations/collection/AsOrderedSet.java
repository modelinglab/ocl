/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * An OrderedSet that contains all the elements from self, with duplicates removed, in an order 
 * dependent on the particular concrete collection type.
 * <p><code>
 * asOrderedSet() : OrderedSet(T)<br/>
 * post: result->forAll(elem | self->includes(elem))<br/>
 * post: self ->forAll(elem | result->includes(elem))<br/>
 * </code></p>

 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class AsOrderedSet extends Operation {

    private static final long serialVersionUID = 1L;

    public AsOrderedSet(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        OrderedSetType resultType = new OrderedSetType();
        resultType.setElementType(tType);

        setName("asOrderedSet");
        setType(resultType);
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new AsOrderedSet(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static AsOrderedSet createTemplateOperation() {
        return new AsOrderedSet(null, TemplateParameterType.getGenericCollectionElement());
    }
}
