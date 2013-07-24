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
 * The intersection of self and bag.
 * <p><code>
 * intersection(bag : Bag(T)) : Set(T)<br/>
 * post: result = self->intersection( bag->asSet )<br/>
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class IntersectionBag extends Operation {
    private static final long serialVersionUID = 1L;

    public static IntersectionBag createTemplateOperation() {
        return new IntersectionBag(null, TemplateParameterType.getGenericCollectionElement());
    }

    public IntersectionBag(Operation templateOperation, Classifier elementType) {
        super(templateOperation);
        
        SetType st = new SetType();
        st.setElementType(elementType);
        
        Classifier resultType = st;
        
        BagType parameterType = new BagType();
        parameterType.setElementType(elementType);
                
        Parameter bag = new Parameter();
        bag.setName("bag");
        bag.setType(parameterType);
        addOwnedParameter(bag);
        
        setName("intersection");
        setType(resultType);
        setSource(st);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new IntersectionBag(this.getTemplateOperation(), ((SetType) sourceType).getElementType());
    }
}
