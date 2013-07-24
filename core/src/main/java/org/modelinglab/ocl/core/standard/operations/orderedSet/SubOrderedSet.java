/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.orderedSet;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class SubOrderedSet extends Operation {

    private static final long serialVersionUID = 1L;

    public static SubOrderedSet createTemplateOperation() {
        return new SubOrderedSet(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public SubOrderedSet(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        OrderedSetType ost = new OrderedSetType();
        ost.setElementType(tType);
        
        Classifier resultType = ost;
        
        setName("subOrderedSet");
        setType(resultType);
        setSource(ost);
        
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
        return new SubOrderedSet(this.getTemplateOperation(), ((OrderedSetType) sourceType).getElementType());
    }
}
