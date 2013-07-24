/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.integer;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * The value of self divided by i.Evaluates to OclInvalid if r is equal to zero.
 * <p>
 * <code>
 * / (i : Integer) : Real
 * </code>
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Division extends Operation {

    private static final long serialVersionUID = 1L;
    private static final Division instance = new Division();

    public static Division getInstance() {
        return instance;
    }

    private Division() {
        super(null);

        Parameter i = new Parameter();
        i.setName("i");
        i.setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        addOwnedParameter(i);

        setName("/");
        setType(PrimitiveType.getInstance(PrimitiveKind.REAL));
        setSource(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
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
