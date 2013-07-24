/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.oclAny;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * Evaluates to true if the type of self conforms to t. That is, self is of type t or a subtype of 
 * t.
 * <p>
 * post: self.oclType().conformsTo(type)
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class OclIsKindOf extends Operation {
    private static final long serialVersionUID = 1L;

    public OclIsKindOf(Operation templateOperation) {
        super(templateOperation);
        
        Parameter newType = new Parameter();
        newType.setName("type");
        ClassifierType parameterType = AnyType.getInstance().getClassifierType();
        newType.setType(parameterType);
        addOwnedParameter(newType);
        
        setName("oclIsKindOf");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(AnyType.getInstance());
    }
    
    public static OclIsKindOf createTemplateOperation() {
        return new OclIsKindOf(null);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }
}
