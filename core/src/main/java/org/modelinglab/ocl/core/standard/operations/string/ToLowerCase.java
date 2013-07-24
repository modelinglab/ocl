/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * Converts self to lower case, if appropriate to the locale. Otherwise, returns the same string as self.
 * <p><code>toLowerCase() : Integer</code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class ToLowerCase extends Operation {

    private static final long serialVersionUID = 1L;
    private static final ToLowerCase instance = new ToLowerCase();
    
    public static ToLowerCase getInstance() {
        return instance;
    }
    
    private ToLowerCase() {
        super(null);

        setName("toLowerCase");
        setType(PrimitiveType.getInstance(PrimitiveKind.STRING));
        setSource(PrimitiveType.getInstance(PrimitiveKind.STRING));
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
