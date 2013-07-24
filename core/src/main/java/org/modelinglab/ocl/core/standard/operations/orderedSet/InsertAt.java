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
public class InsertAt extends Operation {

    private static final long serialVersionUID = 1L;

    public static InsertAt createTemplateOperation() {
        return new InsertAt(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public InsertAt(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        OrderedSetType ost = new OrderedSetType();
        ost.setElementType(tType);
        
        Classifier resultType = ost;
        
        setName("insertAt");
        setType(resultType);
        setSource(ost);
        
        Parameter param = new Parameter();
        param.setName("index");
        param.setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        addOwnedParameter(param);
        
        param = new Parameter();
        param.setName("object");
        param.setType(tType);
        addOwnedParameter(param);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new InsertAt(this.getTemplateOperation(), ((OrderedSetType) sourceType).getElementType());
    }
}
