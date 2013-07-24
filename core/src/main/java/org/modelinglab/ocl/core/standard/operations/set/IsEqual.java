/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.set;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;
/**
 * Evaluates to true if self and s contain the same elements.
 * <p><code>
 * = (s : Set(T)) : Boolean<br/>
 * post: result = (self->forAll(elem | s->includes(elem)) and<br/>
 * s->forAll(elem | self->includes(elem)) )<br/>
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class IsEqual extends Operation {
    private static final long serialVersionUID = 1L;

    public static IsEqual createTemplateOperation() {
        return new IsEqual(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public IsEqual(Operation templateOperation, Classifier elementType) {
        super(templateOperation);
        
        SetType st = new SetType();
        st.setElementType(elementType);
        
        Classifier resultType = PrimitiveType.getInstance(PrimitiveKind.BOOLEAN);
                
        Parameter s = new Parameter();
        s.setName("s");
        s.setType(st);
        addOwnedParameter(s);
        
        setName("=");
        setType(resultType);
        setSource(st);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new IsEqual(this.getTemplateOperation(), ((SetType) sourceType).getElementType());
    }
     
}
