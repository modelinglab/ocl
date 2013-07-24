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
 * The number of characters in self.
 * <p>
 * <code>
 * size() : Integer
 * </code>
 * </p>

 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Size extends Operation {
    private static final long serialVersionUID = 1L;
    private static Size instance = new Size();
    
    public static Size getInstance() {
        return instance;
    }

    private Size() {
        super(null);
        
        setName("size");
        setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
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
