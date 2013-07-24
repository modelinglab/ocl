/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values;

import org.modelinglab.ocl.core.values.utils.ValueVisitor;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.InvalidType;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class InvalidValue extends OclValue<InvalidValue> {
    private static final long serialVersionUID = 1L;
    private static final InvalidValue anInstance = new InvalidValue();
    
    private InvalidValue() {
        super(null);
    }
    
    public static InvalidValue instantiate() {
        return anInstance;
    }

    @Override
    public InvalidValue getValue() {
        return this;
    }

    @Override
    public String toString() {
        return "invalid";
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return instantiate();
    }   

    @Override
    public <Result, Arg> Result accept(ValueVisitor<Result, Arg> visitor, Arg arg) {
        return visitor.visit(this, arg);
    }

    @Override
    public Classifier getType() {
        return InvalidType.getInstance();
    }
}
