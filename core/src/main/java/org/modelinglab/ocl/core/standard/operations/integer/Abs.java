/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.integer;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * The absolute value of self.
 * <p>
 * <code>
 * abs() : Integer<br/>
 * post: if self &lt; 0 then result = - self else result = self endif<br/>
 * </code>
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Abs extends Operation {

    private static final long serialVersionUID = 1L;
    private static final Abs instance = new Abs();

    public static Abs getInstance() {
        return instance;
    }

    private Abs() {
        super(null);

        setName("abs");
        setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        setSource(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }

    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }
}
