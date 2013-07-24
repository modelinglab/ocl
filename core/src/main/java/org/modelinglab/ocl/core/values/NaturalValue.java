/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values;

import org.modelinglab.ocl.core.values.utils.ValueVisitor;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import java.math.BigInteger;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class NaturalValue extends OclValue<BigInteger> {
    private static final long serialVersionUID = 1L;

    public NaturalValue(BigInteger value) {
        super(value);
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
        return PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.UNLIMITED_NATURAL);
    }
    
}
