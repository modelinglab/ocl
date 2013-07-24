/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.bag;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Including extends Operation {

    private static final long serialVersionUID = 1L;

    public static Including createTemplateOperation() {
        return new Including(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public Including(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        BagType bt = new BagType();
        bt.setElementType(tType);
        
        Classifier resultType = bt;
        
        setName("including");
        setType(resultType);
        setSource(bt);
        
        Parameter param = new Parameter();
        param.setName("param");
        param.setType(tType);
        addOwnedParameter(param);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new Including(this.getTemplateOperation(), ((BagType) sourceType).getElementType());
    }
}
