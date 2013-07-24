/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values;

import org.modelinglab.ocl.core.values.utils.ValueVisitor;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class BooleanValue extends OclValue<Boolean> {
    private static final long serialVersionUID = 1L;
    
    public static final BooleanValue TRUE = new BooleanValue(Boolean.TRUE);
    public static final BooleanValue FALSE = new BooleanValue(Boolean.FALSE);

    private BooleanValue(Boolean value) {
        super(value);
    }
    
    public static BooleanValue createValue(boolean value) {
        if (value) {
            return TRUE;
        }
        else {
            return FALSE;
        }
    }
    
    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        if (getValue()) {
            return TRUE;
        }
        else {
            return FALSE;
        }
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
    
    @Override
    public <Result, Arg> Result accept(ValueVisitor<Result, Arg> visitor, Arg arg) {
        return visitor.visit(this, arg);
    }

    @Override
    public Classifier getType() {
        return PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
    }
    
}
