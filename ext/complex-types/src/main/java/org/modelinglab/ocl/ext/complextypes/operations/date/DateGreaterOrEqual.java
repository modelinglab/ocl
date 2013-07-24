/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.operations.date;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class DateGreaterOrEqual extends Operation {
    private static final long serialVersionUID = 1L;
    private static final DateGreaterOrEqual INSTANCE = new DateGreaterOrEqual();
    
    DateGreaterOrEqual() {
        super(null);

        Parameter d = new Parameter();
        d.setName("d");
        d.setType(AGDate.INSTANCE);
        addOwnedParameter(d);

        setName(">=");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(AGDate.INSTANCE);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }

    public static DateGreaterOrEqual getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }
}
