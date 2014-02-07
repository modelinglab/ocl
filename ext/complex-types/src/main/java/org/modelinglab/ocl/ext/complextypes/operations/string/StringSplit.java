/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;

/**
 *
 */
public class StringSplit extends Operation {
    private static final long serialVersionUID = 1L;
    private static final StringSplit INSTANCE = new StringSplit();
    
    StringSplit() {
        super(null);
        
        Parameter regexp = new Parameter();
        regexp.setName("regexp");
        regexp.setType(PrimitiveType.STRING);
        addOwnedParameter(regexp);
        
        setName("split");
        
        CollectionType resultType = CollectionType.newCollection(CollectionType.CollectionKind.SEQUENCE);
        resultType.setElementType(PrimitiveType.STRING);
        
        setType(resultType);
        setSource(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }

    public static StringSplit getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }
}