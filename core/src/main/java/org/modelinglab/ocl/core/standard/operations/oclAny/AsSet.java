/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.oclAny;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class AsSet extends Operation {

    private static final long serialVersionUID = 1L;

    public static AsSet createTemplateOperation() {
        return new AsSet(null, TemplateParameterType.getGenericSourceElement());
    }
    
    public AsSet(Operation templateOperation, Classifier source) {
        super(templateOperation);
        
        SetType resultType = new SetType();
        if (source instanceof CollectionType) {
            resultType.setElementType(((CollectionType)source).getElementType());
        }
        else {
            resultType.setElementType(source);
        }
        
        setName("asSet");
        setType(resultType);
        setSource(AnyType.getInstance());
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new AsSet(this, sourceType);
    }
}
