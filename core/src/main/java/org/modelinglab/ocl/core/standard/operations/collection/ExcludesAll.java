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
 * Does self contain none of the elements of c2 ?
 * <p><code>
 * excludesAll(c2 : Collection(T)) : Boolean<br/>
 * post: result = c2->forAll(elem | self->excludes(elem))<br/>
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class ExcludesAll extends Operation {

    private static final long serialVersionUID = 1L;

    public ExcludesAll(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        Parameter c = new Parameter();
        c.setName("c");
        c.setType(ct);
        addOwnedParameter(c);

        setName("excludesAll");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new ExcludesAll(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static ExcludesAll createTemplateOperation() {
        return new ExcludesAll(null, TemplateParameterType.getGenericCollectionElement());
    }
}
