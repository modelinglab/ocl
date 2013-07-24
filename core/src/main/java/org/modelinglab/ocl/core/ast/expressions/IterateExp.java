/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class IterateExp extends LoopExp {
    private static final long serialVersionUID = 1L;
    
    private Variable result;

    public IterateExp() {
    }

    protected IterateExp(IterateExp other) {
        super(other);
        setResult(other.result.clone());
    }

    public Variable getResult() {
        return result;
    }

    public void setResult(Variable result) {
        modifyAttempt();
        changeChild(this.result, result);
        this.result = result;
    }

    @Override
    public Classifier getType() {
        Preconditions.checkState(getResult() != null,
                "The result attribute of this IterateExp is not set.");
        return getResult().getType();
    }

    @Override
    public String getName() {
        return "Iterate";
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        return null;
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> immutableChildren = super.getTreeChildren();
        immutableChildren.add(getResult());
        return immutableChildren;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IterateExp other = (IterateExp) obj;
        if (this.result != other.result && (this.result == null || !this.result.equals(other.result))) {
            return false;
        }
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.result != null ? this.result.hashCode() : 0);
        return 61 * hash + super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getSource() == null) {
            sb.append("<nullSource>");
        }
        else {
            sb.append(getSource().toString());
        }
        sb.append("->iterate(").append(Joiner.on(", ").join(getIterators()));
        if (getResult() == null) {
            sb.append("<nullResult>");
        }
        else {
            sb.append(getResult().toString());
        }
        sb.append(" | ");
        if (getBody() == null) {
            sb.append("<nullBody>");
        }
        else {
            sb.append(getBody().toString());
        }
        sb.append(')');
        return sb.toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public IterateExp clone() {
        return new IterateExp(this);
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
