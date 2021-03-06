/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.BagType;
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
public class AsBag extends Operation {

    private static final long serialVersionUID = 1L;

    public static AsBag createTemplateOperation() {
        return new AsBag(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public AsBag(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        OrderedSetType ost = new OrderedSetType();
        ost.setElementType(tType);
        
        BagType resultType = new BagType();
        resultType.setElementType(tType);
        
        setName("asBag");
        setType(resultType);
        setSource(ost);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new AsBag(this.getTemplateOperation(), ((OrderedSetType) sourceType).getElementType());
    }
}
