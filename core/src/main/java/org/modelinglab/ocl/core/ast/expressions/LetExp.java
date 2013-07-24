/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

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
public final class LetExp extends OclExpression {
    private static final long serialVersionUID = 1L;

    private OclExpression in;
    private Variable variable;

    public LetExp() {
    }

    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, 
     * associations and operations are NOT cloned!</b>
     */
    protected LetExp(LetExp other) {
        super(other);
        setIn(other.in.clone());
        setVariable(other.variable.clone());
    }
    
    @Override
    public Classifier getType() {
        return in.getType();
    }

    @Override
    public String getName() {
        return "Let "+variable.getName();
    }

    public OclExpression getIn() {
        return in;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setIn(OclExpression in) {
        modifyAttempt();
        changeChild(this.in, in);
        this.in = in;
    }

    public void setVariable(Variable variable) {
        modifyAttempt();
        changeChild(this.variable, variable);
        this.variable = variable;
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        return in.getStaticEvaluation();
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.add(getIn());
        result.add(getVariable());
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
        final LetExp other = (LetExp) obj;
        if (this.in != other.in && (this.in == null || !this.in.equals(other.in))) {
            return false;
        }
        if (this.variable != other.variable && (this.variable == null || !this.variable.equals(other.variable))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.in != null ? this.in.hashCode() : 0);
        hash = 59 * hash + (this.variable != null ? this.variable.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("let ");
        if (getVariable() == null) {
            sb.append("<nullVariable>");
        }
        else {
            sb.append(getVariable().toString());
        }
        sb.append(" in ");
        if (getIn() == null) {
            sb.append("<nullIn>");
        }
        else {
            sb.append(getIn().toString());
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public LetExp clone() {
        return new LetExp(this);
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
