/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * The Set containing all the elements from self, with duplicates removed.
 * <p><code>
 * asSet() : Set(T)<br/>
 * post: result->forAll(elem | self ->includes(elem))<br/>
 * post: self ->forAll(elem | result->includes(elem))<br/>
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class AsSet extends Operation {

    private static final long serialVersionUID = 1L;

    public AsSet(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        SetType resultType = new SetType();
        resultType.setElementType(tType);

        setName("asSet");
        setType(resultType);
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new AsSet(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static AsSet createTemplateOperation() {
        return new AsSet(null, TemplateParameterType.getGenericCollectionElement());
    }
}
