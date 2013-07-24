/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateHour;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StringEndsWith extends Operation {
    private static final long serialVersionUID = 1L;
    private static final StringEndsWith INSTANCE = new StringEndsWith();
    
    StringEndsWith() {
        super(null);
        
        Parameter subString = new Parameter();
        subString.setName("substring");
        subString.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        addOwnedParameter(subString);
        
        setName("endsWith");
        setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        setSource(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }

    public static StringEndsWith getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }
}