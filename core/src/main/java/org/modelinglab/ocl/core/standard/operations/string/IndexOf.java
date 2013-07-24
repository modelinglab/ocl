/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * Queries the index in self at which s is a substring of self, or zero if s is not a substring of 
 * self. The empty string is considered to be a substring of every string but the empty string, at 
 * index 1. No string is a substring of the empty string.
 * 
 * <p>
 * <code>
 * indexOf(s : String) : Integer<br/>
 * post: self.size() = 0 implies result = 0<br/>
 * post: s.size() = 0 and self.size() > 0 implies result = 1<br/>
 * post: s.size() > 0 and result > 0 implies self.substring(result, result + s.size() - 1) = s<br/>
 * </code>
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class IndexOf extends Operation {

    private static final long serialVersionUID = 1L;
    private static final IndexOf instance = new IndexOf();
    
    private IndexOf() {
        super(null);

        Parameter s = new Parameter();
        s.setName("s");
        s.setType(PrimitiveType.getInstance(PrimitiveKind.STRING));
        addOwnedParameter(s);

        setName("indexOf");
        setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        setSource(PrimitiveType.getInstance(PrimitiveKind.STRING));
    }
    
    public static IndexOf getInstance() {
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
