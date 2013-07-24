/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.operations.umlEnum;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;

/**
 *
 */
public class EnumName extends Operation {
    private static final long serialVersionUID = 1L;
    private static final EnumName INSTANCE = new EnumName();

    EnumName() {
        super(null);
        
        setName("name");
        setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        setSource(UmlEnum.getGenericInstance());
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }

    public static EnumName getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }

}
