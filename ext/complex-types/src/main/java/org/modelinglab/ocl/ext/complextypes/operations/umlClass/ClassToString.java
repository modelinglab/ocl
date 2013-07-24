/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.complextypes.operations.umlClass;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import static org.modelinglab.ocl.ext.complextypes.operations.umlClass.ClassToString.getInstance;

/**
 *
 */
public class ClassToString extends Operation {
    private static final long serialVersionUID = 1L;
    private static final ClassToString INSTANCE = new ClassToString();

    ClassToString() {
        super(null);
        
        setName("toString");
        setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        setSource(UmlClass.getGenericInstance());
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }

    public static ClassToString getInstance() {
        return INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }

}
