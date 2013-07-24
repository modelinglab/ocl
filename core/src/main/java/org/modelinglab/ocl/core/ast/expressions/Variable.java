/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.TypedElement;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class Variable extends TypedElement implements Cloneable {

    private static final long serialVersionUID = 1L;
    private OclExpression initExpression;
    
    private Classifier type;

    public Variable() {
    }

    public Variable(String name) {
        setName(name);
    }
    
    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, 
     * associations and operations are NOT cloned!</b>
     */
    protected Variable(Variable other) {
        super(other);
        if (other.initExpression != null) {
            this.initExpression = other.initExpression.clone();
        }
        type = other.type;
    }

    public boolean isTuplePart() {
        return getTreeParent() instanceof TupleLiteralExp;
    }
    
    public Variable(OclExpression initExpression, Classifier type) {
        this.initExpression = initExpression;
        this.type = type;
    }

    public Variable(String name, OclExpression initExpression, Classifier type) {
        setName(name);
        this.initExpression = initExpression;
        this.type = type;
    }

    public OclExpression getInitExpression() {
        return initExpression;
    }

    public void setInitExpression(OclExpression initExpression) {
        this.initExpression = initExpression;
    }

    @Override
    public Classifier getType() {
        return type;
    }

    @Override
    public void setType(Classifier type) {
        this.type = type;
    }
    
    public OclExpression getScope() {
        Element parent = getTreeParent();
        if (!(parent instanceof OclExpression)) {
            return null;
        }
        return (OclExpression) parent;
    }
    
    public boolean isFreeVariable() {
        return getScope() == null;
    }
    
    public boolean isBoundVariable() {
        return getScope() != null;
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.add(initExpression);
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
        final Variable other = (Variable) obj;
        if (this.initExpression != other.initExpression && (this.initExpression == null || !this.initExpression.equals(other.initExpression))) {
            return false;
        }
        if (this.type != other.type && (this.type == null || !this.type.equals(other.type))) {
            return false;
        }
        return super.elementEquals(other);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.initExpression != null ? this.initExpression.hashCode() : 0);
        hash = 79 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }

    /**
     * Clones the variable and its init expression. It is very important to kwnow that <b>UML 
     * classes, attributes, associations and operations are NOT cloned!</b>
     */
    @Override
    public Variable clone() {
        return new Variable(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getName() == null) {
            sb.append("<nullVarName>");
        } else {
            sb.append(getName());
        }
        sb.append(" : ");
        if (getType() == null) {
            sb.append("<nullType>");
        } else {
            sb.append(getType().toString());
        }
        if (getInitExpression() != null) {
            sb.append(" = ").append(getInitExpression().toString());
        }
        return sb.toString();
    }
    
    public <Result, Arg> Result accept(OclExpressionsVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
