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
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.StringValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class StringLiteralExp extends PrimitiveLiteralExp<String> {
    private static final long serialVersionUID = 1L;

    public StringLiteralExp(String value) {
        super(value);
    }

    public StringLiteralExp() {
    }

    @Override
    public Classifier getType() {
        return PrimitiveType.getInstance(PrimitiveKind.STRING);
    }

    @Override
    public String getName() {
        return PrimitiveType.getInstance(PrimitiveKind.STRING).getName();
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        return new StringValue(getValue());
    }
    
    @Override
    public String toString() {
        return "'" + getValue() + '\'';
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public StringLiteralExp clone() {
        return new StringLiteralExp(getValue());
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
