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
 * <code>
 * concat(s : String) : String<br/>
 * The concatenation of self and s.<br/>
 * post: result.size() = self.size() + string.size()<br/>
 * post: result.substring(1, self.size() ) = self<br/>
 * post: result.substring(self.size() + 1, result.size() ) = s<br/>
 * </code>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Concat extends Operation {

    private static final long serialVersionUID = 1L;
    private static final Concat instance = new Concat();
    
    private Concat() {
        super(null);

        Parameter s = new Parameter();
        s.setName("s");
        s.setType(PrimitiveType.getInstance(PrimitiveKind.STRING));
        addOwnedParameter(s);

        setName("concat");
        setType(PrimitiveType.getInstance(PrimitiveKind.STRING));
        setSource(PrimitiveType.getInstance(PrimitiveKind.STRING));
    }
    
    public static Concat getInstance() {
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
