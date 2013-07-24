/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.bag;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class IntersectionSet extends Operation {

    private static final long serialVersionUID = 1L;

    public static IntersectionSet createTemplateOperation() {
        return new IntersectionSet(null, TemplateParameterType.getGenericCollectionElement());
    }
    
    public IntersectionSet(Operation templateOperation, Classifier tType) {
        super(templateOperation);
        
        BagType bt = new BagType();
        bt.setElementType(tType);
        
        SetType resultType = new SetType();
        resultType.setElementType(tType);
        
        setName("intersection");
        setType(resultType);
        setSource(bt);
        
        Parameter param = new Parameter();
        param.setName("param");
        param.setType(resultType);
        addOwnedParameter(param);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new IntersectionSet(this.getTemplateOperation(), ((BagType) sourceType).getElementType());
    }
}
