/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import com.google.common.base.Preconditions;
import java.util.List;
import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class VariableExp extends OclExpression {
    private static final long serialVersionUID = 1L;
    
    private Variable referredVariable;

    public VariableExp() {
    }

    public VariableExp(Variable referredVariable) {
        this.referredVariable = referredVariable;
    }

    public VariableExp(VariableExp other) {
        super(other);
        setReferredVariable(other.getReferredVariable().clone());
    }

    public Variable getReferredVariable() {
        return referredVariable;
    }

    public void setReferredVariable(Variable referredVariable) {
        modifyAttempt();
        //gortiz: a VariableExp cannot be the variable elementParent of an Variable!
        this.referredVariable = referredVariable;
    }

    @Override
    public Classifier getType() {
        Preconditions.checkState(getReferredVariable() != null,
                "The referredVariable of this VariableExp is not set.");
        return getReferredVariable().getType();
    }

    @Override
    public String getName() {
        Preconditions.checkState(getReferredVariable() != null,
                "The referredVariable of this VariableExp is not set.");
        return getReferredVariable().getName();
    }

    @Override
    public OclValue getStaticEvaluation() {
        return null;
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.add(referredVariable);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VariableExp other = (VariableExp) obj;
        if (this.referredVariable != other.referredVariable && (this.referredVariable == null || !this.referredVariable.equals(other.referredVariable))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.referredVariable != null ? this.referredVariable.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        if (getReferredVariable() == null) {
            return "<nullVariable>";
        }
        return referredVariable.getName();
    }

    /**
     * {@inheritDoc }
    */
    @Override
    public VariableExp clone() {
        return new VariableExp(this);
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
