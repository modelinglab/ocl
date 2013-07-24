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
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class BooleanLiteralExp extends PrimitiveLiteralExp<Boolean> {

    private static final long serialVersionUID = 1L;
    
    public BooleanLiteralExp(Boolean value) {
        super(value);
    }

    @Override
    public Classifier getType() {
        return PrimitiveType.getInstance(PrimitiveKind.BOOLEAN);
    }

    @Override
    public String getName() {
        return "Boolean";
    }

    @Override
    public String toString() {
        if (getValue() == null) {
            return "<nullBooleanLiteral>";
        }
        return getValue().toString();
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        if (getValue()) {
            return BooleanValue.TRUE;
        }
        else {
            return BooleanValue.FALSE;
        }
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public BooleanLiteralExp clone() {
        return new BooleanLiteralExp(this.getValue());
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
