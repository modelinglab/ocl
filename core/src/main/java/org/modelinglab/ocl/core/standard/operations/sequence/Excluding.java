/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.sequence;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Excluding extends Operation {

    private static final long serialVersionUID = 1L;

    public static Excluding createTemplateOperation() {
        return new Excluding(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public Excluding(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        SequenceType st = new SequenceType();
        st.setElementType(tType);
        
        Classifier resultType = st;
        
        setName("excluding");
        setType(resultType);
        setSource(st);
        
        Parameter param = new Parameter();
        param.setName("param");
        param.setType(tType);
        addOwnedParameter(param);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new Excluding(this.getTemplateOperation(), ((SequenceType) sourceType).getElementType());
    }
}
