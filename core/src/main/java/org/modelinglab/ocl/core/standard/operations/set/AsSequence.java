/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.set;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class AsSequence extends Operation {

    private static final long serialVersionUID = 1L;

    public static AsSequence createTemplateOperation() {
        return new AsSequence(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public AsSequence(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        SetType st = new SetType();
        st.setElementType(tType);
        
        SequenceType resultType = new SequenceType();
        resultType.setElementType(tType);
        
        setName("asSequence");
        setType(resultType);
        setSource(st);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new AsSequence(this.getTemplateOperation(), ((SetType) sourceType).getElementType());
    }
}
