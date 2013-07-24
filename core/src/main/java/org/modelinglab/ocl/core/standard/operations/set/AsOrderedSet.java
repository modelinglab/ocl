/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.set;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class AsOrderedSet extends Operation {

    private static final long serialVersionUID = 1L;

    public static AsOrderedSet createTemplateOperation() {
        return new AsOrderedSet(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public AsOrderedSet(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        SetType st = new SetType();
        st.setElementType(tType);
        
        OrderedSetType resultType = new OrderedSetType();
        resultType.setElementType(tType);
        
        setName("asOrderedSet");
        setType(resultType);
        setSource(st);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new AsOrderedSet(this.getTemplateOperation(), ((SetType) sourceType).getElementType());
    }
}
