/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.operations.date;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;
import org.modelinglab.ocl.ext.complextypes.classes.AGTimeUnit;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class DateModule extends Operation {
    private static final long serialVersionUID = 1L;
    private static final DateModule INSTANCE = new DateModule();

    DateModule() {
        super(null);
        
        Parameter timeUnit = new Parameter();
        timeUnit.setName("timeUnit");
        timeUnit.setType(AGTimeUnit.INSTANCE);
        addOwnedParameter(timeUnit);
        
        setName("module");
        setType(AGDate.INSTANCE);
        setSource(AGDate.INSTANCE);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }

    public static DateModule getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }
}
