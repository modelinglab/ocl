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
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IntegerLiteralExp extends NumericLiteralExp<Long> {
    private static final long serialVersionUID = 1L;

    public IntegerLiteralExp() {
    }

    public IntegerLiteralExp(Long value) {
        super(value);
    }

    @Override
    public Classifier getType() {
        return PrimitiveType.getInstance(PrimitiveKind.INTEGER);
    }

    @Override
    public String getName() {
        return PrimitiveType.getInstance(PrimitiveKind.INTEGER).getName();
    }

    @Override
    public OclValue<?> getStaticEvaluation() {
        return new IntegerValue(getValue());
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public IntegerLiteralExp clone() {
        return new IntegerLiteralExp(getValue());
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
