/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.InvalidType;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class InvalidLiteralExp extends LiteralExp {

    private static final long serialVersionUID = 1L;

    @Override
    public Classifier getType() {
        return InvalidType.getInstance();
    }

    @Override
    public String getName() {
        return InvalidType.getInstance().getName();
    }

    @Override
    public OclValue getStaticEvaluation() {
        return InvalidValue.instantiate();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof InvalidLiteralExp;
    }

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public String toString() {
        return "invalid";
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public InvalidLiteralExp clone() {
        return new InvalidLiteralExp();
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
