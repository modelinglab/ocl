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
 * The set containing all elements of self plus object.
 * <p><code>
 * including(object : T) : Set(T)<br/>
 * post: result->forAll(elem | self->includes(elem) or (elem = object))<br/>
 * post: self- >forAll(elem | result->includes(elem))<br/>
 * post: result->includes(object)
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Including extends Operation {

    private static final long serialVersionUID = 1L;

    public static Including createTemplateOperation() {
        return new Including(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public Including(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        SetType st = new SetType();
        st.setElementType(tType);
        
        Classifier resultType = st;
        
        setName("including");
        setType(resultType);
        setSource(st);
        
        Parameter s = new Parameter();
        s.setName("s");
        s.setType(tType);
        addOwnedParameter(s);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new Including(this.getTemplateOperation(), ((SetType) sourceType).getElementType());
    }
}
