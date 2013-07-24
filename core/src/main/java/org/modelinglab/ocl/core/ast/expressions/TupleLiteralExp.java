/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import com.google.common.base.Joiner;
import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TupleType;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.ast.utils.OrderedSet;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class TupleLiteralExp extends LiteralExp {

    private static final long serialVersionUID = 1L;
    private OrderedSet<TupleLiteralPart> parts;

    public TupleLiteralExp() {
        parts = new OrderedSet<TupleLiteralPart>();
    }

    public TupleLiteralExp(TupleLiteralExp other) {
        super(other);
        parts = new OrderedSet<TupleLiteralPart>(other.parts.size());
        for (TupleLiteralPart otherPart : other.parts) {
            addPart(otherPart.clone());
        }
    }

    public OrderedSet<TupleLiteralPart> getParts() {
        return parts.clone();
    }

    public void addPart(TupleLiteralPart partToAdd) {
        modifyAttempt();
        changeChild(null, partToAdd);
        parts.add(partToAdd);
    }

    public boolean removePart(TupleLiteralPart partToRemove) {
        modifyAttempt();
        changeChild(partToRemove, null);
        return parts.remove(partToRemove);
    }

    public void setParts(Collection<? extends TupleLiteralPart> parts) {
        modifyAttempt();
        for (TupleLiteralPart part : parts) {
            if (part.getTreeParent() != null) {
                throw new IllegalArgumentException("A TupleLiteralPart to add should have no parent!");
            }
        }
        for (TupleLiteralPart part : this.parts) {
            changeChild(part, null);
        }
        for (TupleLiteralPart part : parts) {
            changeChild(null, part);
        }
        this.parts = new OrderedSet<TupleLiteralPart>(parts);
    }

    @Override
    public Classifier getType() {
        TupleType type = new TupleType();

        for (TupleLiteralPart part : getParts()) {
            type.addAttribute(part.getName(), part.getType());
        }
        return type;
    }

    @Override
    public String getName() {
        StringBuilder sb = new StringBuilder(30);
        sb.append("Tuple{");
        if (!parts.isEmpty()) {
            for (TupleLiteralPart tupleLiteralPart : parts) {
                sb.append(tupleLiteralPart.getName()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        //TODO: This expression could be evaluated statically
        return null;
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.addAll(parts);
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
        final TupleLiteralExp other = (TupleLiteralExp) obj;

        if (!this.parts.equals(other.getParts())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        getParts();
        hash = 11 * hash + parts.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tuple{");
        sb.append(Joiner.on(", ").join(getParts()));
        sb.append('}');
        return sb.toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public TupleLiteralExp clone() {
        return new TupleLiteralExp(this);
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
