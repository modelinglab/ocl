/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.bag;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
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
        
        BagType bt = new BagType();
        bt.setElementType(tType);
        
        OrderedSetType resultType = new OrderedSetType();
        resultType.setElementType(tType);
        
        setName("asOrderedSet");
        setType(resultType);
        setSource(bt);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new AsOrderedSet(this.getTemplateOperation(), ((BagType) sourceType).getElementType());
    }
}
