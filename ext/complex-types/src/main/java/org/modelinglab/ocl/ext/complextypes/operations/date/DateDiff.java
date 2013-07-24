/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.operations.date;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;
import org.modelinglab.ocl.ext.complextypes.classes.AGDayOfWeek;
import org.modelinglab.ocl.ext.complextypes.classes.AGTimeUnit;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class DateDiff extends Operation {
    private static final long serialVersionUID = 1L;
    private static final DateDiff INSTANCE = new DateDiff();

    DateDiff() {
        super(null);
        
        Parameter subtrahend = new Parameter();
        subtrahend.setName("subtrahend");
        subtrahend.setType(AGDate.INSTANCE);
        addOwnedParameter(subtrahend);
        
        Parameter timeUnit = new Parameter();
        timeUnit.setName("tu");
        timeUnit.setType(AGTimeUnit.INSTANCE);
        addOwnedParameter(timeUnit);
        
        setName("diff");
        setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        setSource(AGDate.INSTANCE);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }

    public static DateDiff getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }
    
}