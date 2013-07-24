/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.bag;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class IsEqual extends Operation {

    private static final long serialVersionUID = 1L;

    public static IsEqual createTemplateOperation() {
        return new IsEqual(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public IsEqual(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        BagType bt = new BagType();
        bt.setElementType(tType);
        
        Classifier resultType = PrimitiveType.getInstance(PrimitiveKind.BOOLEAN);
        
        setName("=");
        setType(resultType);
        setSource(bt);
        
        Parameter param = new Parameter();
        param.setName("param");
        param.setType(bt);
        addOwnedParameter(param);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new IsEqual(this.getTemplateOperation(), ((BagType) sourceType).getElementType());
    }
}
