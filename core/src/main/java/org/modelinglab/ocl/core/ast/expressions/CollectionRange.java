/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.utils.CollectionLiteralPartVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.OclRuntimeException;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class CollectionRange extends CollectionLiteralPart {
    private static final long serialVersionUID = 1L;
    
    private OclExpression first;
    private OclExpression last;

    public CollectionRange() {
    }

    public CollectionRange(OclExpression first, OclExpression last) {
        setFirst(first.clone());
        setLast(last.clone());
    }
    
    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, 
     * associations and operations are NOT cloned!</b>
     */
    protected CollectionRange(CollectionRange other) {
        super(other);
        first = other.first.clone();
        last = other.last.clone();
    }

    public OclExpression getFirst() {
        return first;
    }

    public void setFirst(OclExpression first) {
        modifyAttempt();
        changeChild(this.first, first);
        this.first = first;
    }

    public OclExpression getLast() {
        return last;
    }

    public void setLast(OclExpression last) {
        modifyAttempt();
        if (!last.getType().equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER))) {
            throw new OclRuntimeException("It was expected that " +last+ ", as last element of a "
                    + "range, was a integer.");
        }
        
        changeChild(this.last, last);
        this.last = last;
    }

    @Override
    public String getName() {
        return getFirst().getName() + ".." + getLast().getName();
    }

    @Override
    public Classifier getType() {
//        return OclUtils.getLowestSharedType(getFirst().getType(), getLast().getType());
        return PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER);
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.add(first);
        result.add(last);
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
        final CollectionRange other = (CollectionRange) obj;
        if (this.first != other.first && (this.first == null || !this.first.equals(other.first))) {
            return false;
        }
        if (this.last != other.last && (this.last == null || !this.last.equals(other.last))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.first != null ? this.first.hashCode() : 0);
        hash = 67 * hash + (this.last != null ? this.last.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (first == null) {
            sb.append("<nullFirstArg>");
        }
        else {
            sb.append(first.toString());
        }
        sb.append("..");
        if (last == null) {
            sb.append("<nullLastArg>");
        }
        else {
            sb.append(last.toString());
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public CollectionRange clone() {
        return new CollectionRange(this);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(CollectionLiteralPartVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
