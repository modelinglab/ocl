/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.oclAny;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * Evaluates to self, where self is of the type identified by t. The type t may be any classifier 
 * defined in the UML model; if the actual type of self at evaluation time does not conform to t, 
 * then the oclAsType operation evaluates to null.
 * <p>
 * In the case of feature redefinition, casting an object to a supertype of its actual type does not
 * access the supertype's definition of the feature; according to the semantics of redefinition, the
 * redefined feature simply does not exist for the object. However, when casting to a supertype, any
 * features additionally defined by the subtype are suppressed.
 * </p>
 * <p>
 * post: (result = self) and result.oclIsTypeOf( t )
 * </p>
 * 
 * <p>
 * <b>The type of this operation depends on its argument!</b>
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class OclAsType extends Operation {
    private static final long serialVersionUID = 1L;
    
    public OclAsType(Operation templateOperation, Classifier parameterType) {
        super(templateOperation);
        
        Parameter newType = new Parameter();
        newType.setName("type");
        newType.setType(parameterType);
        addOwnedParameter(newType);
        
        setName("oclAsType");
        if (parameterType instanceof ClassifierType) {
            setType(((ClassifierType) parameterType).getReferredClassifier());
        }
        else {
            setType(AnyType.getInstance());
        }
        setSource(AnyType.getInstance());
    }
    
    public static OclAsType createTemplateOperation() {
        return new OclAsType(null, new TemplateParameterType("T2"));
    }

    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        Preconditions.checkArgument(argTypes.size() == 1);
        Preconditions.checkArgument(argTypes.get(0) instanceof ClassifierType, "OclAsType only support arguments of type ClassifierType.");
        return new OclAsType(this, argTypes.get(0));
    }
}
