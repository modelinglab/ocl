/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.string;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * Queries whether s and self are equivalent under case-insensitive collation in the locale of self.
 * <p>
 * <code>
 * equalsIgnoreCase(s : String) : Integer
 * </code>
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class EqualsIgnoreCase extends Operation {

    private static final long serialVersionUID = 1L;
    private static final EqualsIgnoreCase instance = new EqualsIgnoreCase();
    
    private EqualsIgnoreCase() {
        super(null);

        Parameter s = new Parameter();
        s.setName("s");
        s.setType(PrimitiveType.getInstance(PrimitiveKind.STRING));
        addOwnedParameter(s);

        setName("equalsIgnoreCase");
        setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        setSource(PrimitiveType.getInstance(PrimitiveKind.STRING));
    }
    public static EqualsIgnoreCase getInstance() {
        return instance;
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
