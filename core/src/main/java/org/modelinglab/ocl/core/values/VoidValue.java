/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values;

import org.modelinglab.ocl.core.values.utils.ValueVisitor;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.VoidType;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class VoidValue extends OclValue<VoidValue> {
    private static final long serialVersionUID = 1L;
    private static final VoidValue anInstance = new VoidValue();
    
    private VoidValue() {
        super(null);
    }
    
    public static VoidValue instantiate() {
        return anInstance;
    }

    @Override
    public VoidValue getValue() {
        return this;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return instantiate();
    }

    @Override
    public String toString() {
        return "null";
    }

    @Override
    public <Result, Arg> Result accept(ValueVisitor<Result, Arg> visitor, Arg arg) {
        return visitor.visit(this, arg);
    }  

    @Override
    public Classifier getType() {
        return VoidType.getInstance();
    }  
}