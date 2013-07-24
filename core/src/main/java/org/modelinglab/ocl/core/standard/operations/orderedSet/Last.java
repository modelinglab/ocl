/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Last extends Operation {

    private static final long serialVersionUID = 1L;

    public static Last createTemplateOperation() {
        return new Last(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public Last(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        OrderedSetType ost = new OrderedSetType();
        ost.setElementType(tType);
        
        Classifier resultType = tType;
        
        setName("last");
        setType(resultType);
        setSource(ost);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new Last(this.getTemplateOperation(), ((OrderedSetType) sourceType).getElementType());
    }
}
