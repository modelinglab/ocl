/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.operations.date;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class DateHour extends Operation {
    private static final long serialVersionUID = 1L;
    private static final DateHour INSTANCE = new DateHour();

    DateHour() {
        super(null);
        
        setName("hour");
        setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        setSource(AGDate.INSTANCE);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }

    public static DateHour getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }
    
}
