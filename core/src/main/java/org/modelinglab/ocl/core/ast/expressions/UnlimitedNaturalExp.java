/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.NaturalValue;
import org.modelinglab.ocl.core.values.OclValue;
import java.math.BigInteger;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class UnlimitedNaturalExp extends NumericLiteralExp<BigInteger> {
    private static final long serialVersionUID = 1L;

    public UnlimitedNaturalExp() {
    }
    
    public UnlimitedNaturalExp(BigInteger value) {
        super(value);
    }

    @Override
    public Classifier getType() {
        return PrimitiveType.getInstance(PrimitiveKind.UNLIMITED_NATURAL);
    }

    @Override
    public String getName() {
        return PrimitiveType.getInstance(PrimitiveKind.UNLIMITED_NATURAL).getName();
    }

    @Override
    public String toString() {
        return getValue().toString() + 'u';
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        return new NaturalValue(getValue());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public UnlimitedNaturalExp clone() {
        return new UnlimitedNaturalExp(getValue());
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclExpressionsVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
    
}
