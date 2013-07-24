/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.set;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * The elements of self, which are not in s.
 * <p><code>
 * – (s : Set(T)) : Set(T)
 * post: result->forAll(elem | self->includes(elem) and s->excludes(elem))<br/>
 * post: self ->forAll(elem | result->includes(elem) = s->excludes(elem))<br/>
 * </code></p>

 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Substraction extends Operation {
    private static final long serialVersionUID = 1L;

    public static Substraction createTemplateOperation() {
        return new Substraction(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public Substraction(Operation templateOperation, Classifier elementType) {
        super(templateOperation);
        
        SetType st = new SetType();
        st.setElementType(elementType);
        
        Classifier resultType = st;
        
        setName("-");
        setType(resultType);
        setSource(st);
        
        Parameter s = new Parameter();
        s.setName("s");
        s.setType(st);
        addOwnedParameter(s);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new Substraction(this.getTemplateOperation(), ((SetType) sourceType).getElementType());
    }
    
}
