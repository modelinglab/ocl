/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 * @deprecated 
 */
@Deprecated
public final class StateExp extends OclExpression {

    private StateExp() {
    }

    private StateExp(OclExpression other) {
        super(other);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Classifier getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public StateExp clone() {
        return new StateExp(this);
    }

    @Override
    public <Result, Arg> Result accept(OclExpressionsVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
    
}
