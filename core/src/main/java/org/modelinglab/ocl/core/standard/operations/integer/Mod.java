/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.integer;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * The result is self modulo i.
 * <p>
 * <code>
 * mod( i : Integer) : Integer<br/>
 * post: result = self - (self.div(i) * i)
 * </code>
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Mod extends Operation {

    private static final long serialVersionUID = 1L;
    private static final Mod instance = new Mod();

    public static Mod getInstance() {
        return instance;
    }

    private Mod() {
        super(null);

        Parameter i = new Parameter();
        i.setName("i");
        i.setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        addOwnedParameter(i);

        setName("mod");
        setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        setSource(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
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
