/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.oclAny;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * Evaluates to true if the self is equal to OclInvalid.
 * <p>
 * post: result = self.isTypeOf( OclInvalid)
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class OclIsInvalid extends Operation {

    private static final long serialVersionUID = 1L;
    private static final OclIsInvalid instance = new OclIsInvalid();

    public static OclIsInvalid getInstance() {
        return instance;
    }

    private OclIsInvalid() {
        super(null);

        setName("oclIsInvalid");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(AnyType.getInstance());
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
