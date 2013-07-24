/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.set;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * 
The intersection of self and s (i.e., the set of all elements that are in both self and s).
 * <p><code>
 * intersection(s : Set(T)) : Set(T)<br/>
 * post: result->forAll(elem | self->includes(elem) and s->includes(elem))<br/>
 * post: self->forAll(elem | s ->includes(elem) = result->includes(elem))<br/>
 * post: s ->forAll(elem | self->includes(elem) = result->includes(elem))<br/>
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class IntersectionSet extends Operation {
    private static final long serialVersionUID = 1L;

    public static IntersectionSet createTemplateOperation() {
        return new IntersectionSet(null, TemplateParameterType.getGenericCollectionElement());
    }

    public IntersectionSet(Operation templateOperation, Classifier elementType) {
        super(templateOperation);
        
        SetType st = new SetType();
        st.setElementType(elementType);
        
        Classifier resultType = st;
                
        Parameter s = new Parameter();
        s.setName("s");
        s.setType(st);
        addOwnedParameter(s);
        
        setName("intersection");
        setType(resultType);
        setSource(st);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new IntersectionSet(this.getTemplateOperation(), ((SetType) sourceType).getElementType());
    }
    
}
