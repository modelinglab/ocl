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
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class SymmetricDifference extends Operation {

    private static final long serialVersionUID = 1L;

    public static SymmetricDifference createTemplateOperation() {
        return new SymmetricDifference(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public SymmetricDifference(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        SetType st = new SetType();
        st.setElementType(tType);
        
        Classifier resultType = st;
        
        setName("symmetricDifference");
        setType(resultType);
        setSource(st);
        
        Parameter s = new Parameter();
        s.setName("s");
        s.setType(st);
        addOwnedParameter(s);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new SymmetricDifference(this.getTemplateOperation(), ((SetType) sourceType).getElementType());
    }
}
