/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclUtils;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.OclValue;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class IfExp extends OclExpression {
    private static final long serialVersionUID = 1L;
    
    private OclExpression condition, elseExpression, thenExpression;

    public IfExp() {
    }

    public IfExp(IfExp other) {
        super(other);
        setCondition(other.condition.clone());
        setCondition(other.elseExpression.clone());
        setCondition(other.thenExpression.clone());
    }

    @Override
    public Classifier getType() {
        return OclUtils.getLowestSharedType(getElseExpression().getType(), getThenExpression().getType());
    }

    public OclExpression getCondition() {
        return condition;
    }

    public void setCondition(OclExpression condition) {
        modifyAttempt();
        changeChild(this.condition, condition);
        this.condition = condition;
    }

    public OclExpression getElseExpression() {
        return elseExpression;
    }

    public void setElseExpression(OclExpression elseExpression) {
        modifyAttempt();
        changeChild(this.elseExpression, elseExpression);
        this.elseExpression = elseExpression;
    }

    public OclExpression getThenExpression() {
        return thenExpression;
    }

    public void setThenExpression(OclExpression thenExpression) {
        modifyAttempt();
        changeChild(this.thenExpression, thenExpression);
        this.thenExpression = thenExpression;
    }

    @Override
    public String getName() {
        return "If";
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        OclValue conditionVal = getCondition().getStaticEvaluation();
        if (conditionVal == null) {
            return null;
        }
        if (!(conditionVal instanceof BooleanValue)) {
            throw new IllegalStateException();//TODO: Add message
        }
        if (conditionVal.equals(BooleanValue.TRUE)) {
            return getThenExpression().getStaticEvaluation();
        }
        else {
            assert conditionVal.equals(BooleanValue.FALSE);
            return getElseExpression().getStaticEvaluation();
        }
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.add(condition);
        result.add(thenExpression);
        result.add(elseExpression);
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
        final IfExp other = (IfExp) obj;
        if (this.condition != other.condition && (this.condition == null || !this.condition.equals(other.condition))) {
            return false;
        }
        if (this.elseExpression != other.elseExpression && (this.elseExpression == null || !this.elseExpression.equals(other.elseExpression))) {
            return false;
        }
        if (this.thenExpression != other.thenExpression && (this.thenExpression == null || !this.thenExpression.equals(other.thenExpression))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.condition != null ? this.condition.hashCode() : 0);
        hash = 89 * hash + (this.elseExpression != null ? this.elseExpression.hashCode() : 0);
        hash = 89 * hash + (this.thenExpression != null ? this.thenExpression.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("if ");
        if (getCondition() == null) {
            sb.append("<nullCondition>");
        }
        else {
            sb.append(getCondition().toString());
        }
        sb.append(" then ");
        if (getThenExpression() == null) {
            sb.append("<nullThenCondition>");
        }
        else {
            sb.append(getThenExpression().toString());
        }
        sb.append(" else ");
        if (getElseExpression() == null) {
            sb.append("<nullElseCondition>");
        }
        else {
            sb.append(getElseExpression().toString());
        }
        sb.append(" endif");
        return sb.toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public IfExp clone() {
        return new IfExp(this);
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
