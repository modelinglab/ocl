/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.sequence;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class SubSequence extends Operation {

    private static final long serialVersionUID = 1L;

    public static SubSequence createTemplateOperation() {
        return new SubSequence(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public SubSequence(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        SequenceType st = new SequenceType();
        st.setElementType(tType);
        
        Classifier resultType = st;
        
        setName("subSequence");
        setType(resultType);
        setSource(st);
        
        Parameter param = new Parameter();
        param.setName("lower");
        param.setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        addOwnedParameter(param);
        
        param = new Parameter();
        param.setName("upper");
        param.setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        addOwnedParameter(param);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new SubSequence(this.getTemplateOperation(), ((SequenceType) sourceType).getElementType());
    }
}
