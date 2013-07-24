/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.ast.utils.UmlVisitor;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class Parameter extends TypedElement {

    private static final long serialVersionUID = 1L;
    Classifier type;
    Operation operation;

    public Parameter() {
    }

    public Parameter(String name, Classifier type, Operation operation) {
        setName(name);
        this.type = type;
        this.operation = operation;
    }

    /**
     * @return The operation that owns the parameter.
     */
    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        modifyAttempt();
        if (getOperation() != null) {
            getOperation().removeOwnedParameterUnsecure(this);
        }
        setOperationUnsecure(operation);
        if (operation != null) {
            operation.addOwnedParameterUnsecure(this);
        }
    }

    public void setOperationUnsecure(Operation operation) {
        this.operation = operation;
    }

    @Override
    public Classifier getType() {
        return type;
    }

    @Override
    public void setType(Classifier type) {
        modifyAttempt();
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Parameter other = (Parameter) obj;
        if (this.type != other.type && (this.type == null || !this.type.equals(other.type))) {
            return false;
        }
        return elementEquals(other);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
    
    public <Result, Arg> Result accept(UmlVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
