/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.operations.date;

import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;

/**
 *
 */
public class DateParse extends Operation {

    private static final DateParse INSTANCE = new DateParse();

    public DateParse() {
        super(null);
        
        Parameter d = new Parameter();
        d.setName("toParse");
        d.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        addOwnedParameter(d);
        
        setName("parse");
        setType(AGDate.INSTANCE);
        setSource(AGDate.INSTANCE.getClassifierType());
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType,
            List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }

    public static DateParse getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }

}
