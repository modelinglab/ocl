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
import javax.annotation.concurrent.Immutable;

/**
 * The addition of all elements in self. Elements must be of a type supporting the + operation. 
 * The + operation must take one parameter of type T and be both associative: (a+b)+c = a+(b+c), 
 * and commutative: a+b = b+a. Integer and Real fulfill this condition.
 * <p><code>
 * sum() : T<br/>
 * post: result = self->iterate( elem; acc : T = 0 | acc + elem )<br/>
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Sum extends Operation {

    private static final long serialVersionUID = 1L;

    public Sum(Operation templateOperation, Classifier tType) {
        super(templateOperation);

        CollectionType ct = new CollectionType();
        ct.setElementType(tType);

        setName("sum");
        setType(tType);
        setSource(ct);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new Sum(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType());
    }

    public static Sum createTemplateOperation() {
        return new Sum(null, TemplateParameterType.getGenericCollectionElement());
    }
}
