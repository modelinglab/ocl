/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.oclVoid;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.ast.types.VoidType;
import javax.annotation.concurrent.Immutable;

/**
 * Redefines the OclAny operation, returning true if object is null.
 * <p>
 * post: result = object.oclIsTypeOf(OclVoid)
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class IsEqual extends Operation {

    private static final long serialVersionUID = 1L;
    private static final IsEqual instance = new IsEqual();

    public static IsEqual getInstance() {
        return instance;
    }

    private IsEqual() {
        super(null);

        Parameter object2 = new Parameter();
        object2.setName("object2");
        object2.setType(AnyType.getInstance());
        addOwnedParameter(object2);

        setName("=");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(VoidType.getInstance());
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }
}
