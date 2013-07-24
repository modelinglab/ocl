/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import javax.annotation.concurrent.Immutable;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public final class NullLiteralExp extends LiteralExp {

    private static final long serialVersionUID = 1L;

    @Override
    public Classifier getType() {
        return VoidType.getInstance();
    }

    @Override
    public String getName() {
        return VoidType.getInstance().getName();
    }

    @Override
    public OclValue<?> getStaticEvaluation() throws OclEvaluationException {
        return VoidValue.instantiate();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof NullLiteralExp;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public String toString() {
        return "null";
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public NullLiteralExp clone() {
        return new NullLiteralExp();
    }

    @Override
    public <Result, Arg> Result accept(OclExpressionsVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
