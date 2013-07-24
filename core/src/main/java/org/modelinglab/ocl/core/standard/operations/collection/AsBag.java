/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * The Bag that contains all the elements from self.
 * <p><code>
 * asBag() : Bag(T)<br/>
 * post: result->forAll(elem | self->includes(elem))<br/>
 * post: self ->forAll(elem | result->includes(elem))<br/>
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class AsBag extends Operation {

    private static final long serialVersionUID = 1L;

    public AsBag(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        BagType resultType = new BagType();
        resultType.setElementType(tType);

        setName("asBag");
        setType(resultType);
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new AsBag(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static AsBag createTemplateOperation() {
        return new AsBag(null, TemplateParameterType.getGenericCollectionElement());
    }
}
