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
public final class UmlEnumLiteral extends TypedElement {
    private static final long serialVersionUID = 1L;

    private UmlEnum type;

    public UmlEnumLiteral(String name) {
        setName(name);
    }

    public void setTypeUnsecure(UmlEnum type) {
        modifyAttempt();
        this.type = type;
    }

    @Override
    public UmlEnum getType() {
        return type;
    }
    
    public int getOrdinal() {
        return type.getLiteralOrdinal(this);
    }

    @Override
    public void setType(Classifier newType) {
        Preconditions.checkArgument(newType instanceof UmlEnum);
        if (type != null) {
            type.removeLiteralUnsecure(this);
        }
        type = (UmlEnum) newType;
        setTypeUnsecure(type);
        if (type != null) {
            type.addLiteralUnsecure(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(30);
        
        if (getType() == null) {
            sb.append("<null>");
        }
        else {
            sb.append(getType().getName());
        }
        sb.append("::");
        if (getName() == null) {
            sb.append("<null>");
        }
        else {
            sb.append(getName());
        }
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UmlEnumLiteral other = (UmlEnumLiteral) obj;
        if (this.type != other.type && (this.type == null || !this.type.equals(other.type))) {
            return false;
        }
        return elementEquals(other);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
    
    public <Result, Arg> Result accept(UmlVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
