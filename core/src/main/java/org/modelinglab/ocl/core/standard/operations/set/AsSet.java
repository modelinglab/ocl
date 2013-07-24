/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.set;

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
public class AsSet extends Operation {

    private static final long serialVersionUID = 1L;

    public static AsSet createTemplateOperation() {
        return new AsSet(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public AsSet(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        SetType st = new SetType();
        st.setElementType(tType);
        
        Classifier resultType = st;
        
        setName("asSet");
        setType(resultType);
        setSource(st);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new AsSet(this.getTemplateOperation(), ((SetType) sourceType).getElementType());
    }
}
