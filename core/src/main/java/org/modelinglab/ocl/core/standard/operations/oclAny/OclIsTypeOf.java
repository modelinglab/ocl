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
 * Evaluates to true if self is of the type t but not a subtype of t
 * <p>
 * post: self.oclType() = type
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class OclIsTypeOf extends Operation {
    private static final long serialVersionUID = 1L;

    public OclIsTypeOf(Operation templateOperation/*, Classifier tType*/) {
        super(templateOperation);
        
        Parameter newType = new Parameter();
        newType.setName("type");
        ClassifierType parameterType = AnyType.getInstance().getClassifierType();
        newType.setType(parameterType);
        addOwnedParameter(newType);
        
        setName("oclIsTypeOf");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(AnyType.getInstance());
    }
    
    public static OclIsTypeOf createTemplateOperation() {
        return new OclIsTypeOf(null/*, new TemplateParameterType("T2")*/);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }
}
