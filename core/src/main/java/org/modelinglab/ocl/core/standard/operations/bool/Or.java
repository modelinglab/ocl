/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.bool;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * True if either self or b is true.
 * <p><code>or (b : Boolean) : Boolean</code></p>

 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Or extends Operation {

    private static final long serialVersionUID = 1L;
    private static final Or instance = new Or();

    public static Or getInstance() {
        return instance;
    }

    private Or() {
        super(null);

        Parameter b = new Parameter();
        b.setName("b");
        b.setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        addOwnedParameter(b);

        setName("or");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
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
