/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.real;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class LessOrEqual extends Operation {

    private static final long serialVersionUID = 1L;
    private static final LessOrEqual instance = new LessOrEqual();

    public static LessOrEqual getInstance() {
        return instance;
    }

    private LessOrEqual() {
        super(null);

        Parameter r = new Parameter();
        r.setName("r");
        r.setType(PrimitiveType.getInstance(PrimitiveKind.REAL));
        addOwnedParameter(r);

        setName("<=");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(PrimitiveType.getInstance(PrimitiveKind.REAL));
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
