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
The sub-string of self starting at character number lower, up to and including character number upper. Character numbers
run from 1 to self.size().
 * <p>
 * <code>
 * substring(lower : Integer, upper : Integer) : String<br/<
 * pre: 1 <= lower<br/>
 * pre: lower <= upper<br/>
 * pre: upper <= self.size()<br/>
 * </code>
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Substring extends Operation {
    private static final long serialVersionUID = 1L;

    private static final Substring instance = new Substring();
    
    public static Substring getInstance() {
        return instance;
    }
    
    private Substring() {
        super(null);
        
        Parameter lower = new Parameter();
        lower.setName("lower");
        lower.setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        addOwnedParameter(lower);
        
        Parameter upper = new Parameter();
        upper.setName("upper");
        upper.setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        addOwnedParameter(upper);
        
        setName("substring");
        setType(PrimitiveType.getInstance(PrimitiveKind.STRING));
        setSource(PrimitiveType.getInstance(PrimitiveKind.STRING));
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }
    
}
