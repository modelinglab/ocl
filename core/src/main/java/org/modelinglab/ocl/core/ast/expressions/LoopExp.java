/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.Element;
import java.util.*;
import javax.annotation.Nonnull;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class LoopExp extends CallExp {

    private List<Variable> iterators = new ArrayList<Variable>(2);
    private OclExpression body;

    public LoopExp() {
    }

    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, associations
     * and operations are NOT cloned!</b>
     */
    protected LoopExp(LoopExp other) {
        super(other);
        iterators = new ArrayList<Variable>(other.iterators.size());
        for (Variable variable : other.iterators) {
            addIterator(variable.clone());
        }
        setBody(other.body.clone());
    }

    public final List<Variable> getIterators() {
        return Collections.unmodifiableList(iterators);
    }

    public final void setIterators(@Nonnull Collection<? extends Variable> iterators) {
        modifyAttempt();
        for (Variable variable : iterators) {
            if (variable.getTreeParent() != null) {
                throw new IllegalArgumentException("Variable " + variable + " already has a parent (" + variable.getTreeParent() + ")!");
            }
        }
        for (Variable variable : this.iterators) {
            changeChild(variable, null);
        }
        for (Variable variable : iterators) {
            changeChild(null, variable);
        }
        this.iterators = new ArrayList<Variable>(iterators);
    }

    public final void addIterator(@Nonnull Variable varToAdd) {
        modifyAttempt();
        changeChild(null, varToAdd);
        iterators.add(varToAdd);
    }

    public final boolean removeIterator(@Nonnull Variable varToDelete) {
        modifyAttempt();
        if (iterators.remove(varToDelete)) {
            changeChild(varToDelete, null);
            return true;
        }
        return false;
    }

    public final OclExpression getBody() {
        return body;
    }

    public final void setBody(OclExpression body) {
        modifyAttempt();
        this.body = body;
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.add(body);
        result.addAll(getIterators());
        return result;
    }

    public boolean equals(LoopExp other) {
        if (this.iterators != other.iterators && (this.iterators == null || !this.iterators.equals(other.iterators))) {
            return false;
        }
        if (this.body != other.body && (this.body == null || !this.body.equals(other.body))) {
            return false;
        }
        String myName = this.getName();
        String otherName = other.getName();
        if (myName != otherName && (myName == null || !myName.equals(otherName))) {
            return false;
        }
        return super.equalsCallExp(other);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.iterators != null ? this.iterators.hashCode() : 0);
        hash = 53 * hash + (this.body != null ? this.body.hashCode() : 0);
        return 53 * hash + super.hashCode();
    }

    @Override
    public abstract boolean equals(Object obj);
}
