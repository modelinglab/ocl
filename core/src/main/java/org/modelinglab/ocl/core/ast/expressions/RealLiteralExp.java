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
import org.modelinglab.ocl.core.values.RealValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class RealLiteralExp extends NumericLiteralExp<Double> {
    private static final long serialVersionUID = 1L;

    public RealLiteralExp() {
    }

    public RealLiteralExp(Double value) {
        super(value);
    }

    @Override
    public Classifier getType() {
        return PrimitiveType.getInstance(PrimitiveKind.REAL);
    }

    @Override
    public String getName() {
        return PrimitiveType.getInstance(PrimitiveKind.REAL).getName();
    }

    @Override
    public RealValue getStaticEvaluation() {
        return new RealValue(getValue());
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public RealLiteralExp clone() {
        return new RealLiteralExp(getValue());
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
