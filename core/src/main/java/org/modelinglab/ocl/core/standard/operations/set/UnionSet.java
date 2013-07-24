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
 * The union of self and s.
 * <p><code>
 * union(s : Set(T)) : Set(T)<br/>
 * post: result->forAll(elem | self->includes(elem) or s->includes(elem))<br/>
 * post: self ->forAll(elem | result->includes(elem))<br/>
 * post: s ->forAll(elem | result->includes(elem))<br/>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class UnionSet extends Operation {
    private static final long serialVersionUID = 1L;

    public UnionSet(Operation templateOperation, Classifier elementType) {
        super(templateOperation);
        
        SetType st = new SetType();
        st.setElementType(elementType);
        
        Classifier resultType = st;
                
        Parameter s = new Parameter();
        s.setName("s");
        s.setType(st);
        addOwnedParameter(s);
        
        setName("union");
        setType(resultType);
        setSource(st);
    }
    
    public static UnionSet createTemplateOperation() {
        return new UnionSet(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new UnionSet(this.getTemplateOperation(), ((SetType) sourceType).getElementType());
    }
    
}
