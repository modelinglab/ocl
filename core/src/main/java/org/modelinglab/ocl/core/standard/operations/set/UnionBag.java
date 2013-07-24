/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.set;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * The union of self and bag.
 * <p><code>
 * union(bag : Bag(T)) : Bag(T)<br/>
 * post: result->forAll(elem | result->count(elem) = self->count(elem) + bag->count(elem))<br/>
 * post: self->forAll(elem | result->includes(elem))<br/>
 * post: bag ->forAll(elem | result->includes(elem))<br/>
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class UnionBag extends Operation {
    private static final long serialVersionUID = 1L;

    public UnionBag(Operation templateOperation, Classifier elementType) {
        super(templateOperation);
        
        SetType st = new SetType();
        st.setElementType(elementType);
        
        BagType resultType = new BagType();
        resultType.setElementType(elementType);
                
        Parameter bag = new Parameter();
        bag.setName("bag");
        bag.setType(resultType);
        addOwnedParameter(bag);
        
        setName("union");
        setType(resultType);
        setSource(st);
    }

    public static UnionBag createTemplateOperation() {
        return new UnionBag(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new UnionBag(this.getTemplateOperation(), ((SetType) sourceType).getElementType());
    }
}
