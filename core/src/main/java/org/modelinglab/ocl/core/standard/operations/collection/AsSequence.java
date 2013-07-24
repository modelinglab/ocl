/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * A Sequence that contains all the elements from self, in an order dependent on the particular concrete collection type.
 * <p><code>
 * asSequence() : Sequence(T)<br/>
 * post: result->forAll(elem | self->includes(elem))<br/>
 * post: self ->forAll(elem | result->includes(elem))<br/>
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class AsSequence extends Operation {

    private static final long serialVersionUID = 1L;

    public AsSequence(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        SequenceType resultType = new SequenceType();
        resultType.setElementType(tType);

        setName("asSequence");
        setType(resultType);
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new AsSequence(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static AsSequence createTemplateOperation() {
        return new AsSequence(null, TemplateParameterType.getGenericCollectionElement());
    }
}
