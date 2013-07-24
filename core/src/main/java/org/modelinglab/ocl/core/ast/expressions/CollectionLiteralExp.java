/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.CollectionType.CollectionKind;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class CollectionLiteralExp extends LiteralExp {

    private static final long serialVersionUID = 1L;
    private LinkedList<CollectionLiteralPart> parts = new LinkedList<CollectionLiteralPart>();
    private CollectionType type;

    public CollectionLiteralExp() {
    }

    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, associations
     * and operations are NOT cloned!</b>
     */
    protected CollectionLiteralExp(CollectionLiteralExp other) {
        super(other);
        type = other.type;
        parts = new LinkedList<CollectionLiteralPart>();
        for (CollectionLiteralPart collectionLiteralPart : other.parts) {
            parts.add(collectionLiteralPart.clone());
        }
    }

    public CollectionKind getKind() {
        return type.getCollectionKind();
    }

    public List<CollectionLiteralPart> getParts() {
        return Collections.unmodifiableList(parts);
    }

    public void setParts(Collection<? extends CollectionLiteralPart> parts) {
        modifyAttempt();
        for (CollectionLiteralPart collectionLiteralPart : parts) {
            if (collectionLiteralPart.getTreeParent() != null) {
                throw new IllegalArgumentException("A CollectionLiteralPart to add should have no parent!");
            }
        }
        for (CollectionLiteralPart part : this.parts) {
            changeChild(part, null);
        }
        for (CollectionLiteralPart part : parts) {
            changeChild(null, part);
        }
        this.parts = new LinkedList<CollectionLiteralPart>(parts);
    }

    public void addPart(CollectionLiteralPart partToAdd) {
        modifyAttempt();
        changeChild(null, partToAdd);
        parts.add(partToAdd);
    }

    public boolean removePart(CollectionLiteralPart partToRemove) {
        modifyAttempt();
        if (parts.remove(partToRemove)) {
            changeChild(partToRemove, null);
            return true;
        }
        return false;
    }

    @Override
    public CollectionType getType() {
        return type;
    }

    @Override
    public void setType(Classifier type) {
        modifyAttempt();
        CollectionType collectionType = (CollectionType) type;
        if (collectionType.getCollectionKind() == CollectionKind.COLLECTION) {
            throw new IllegalArgumentException("In a collection literal the type may be a Set, "
                    + "OrderedSet, Sequence or bag, but not Collection.");
        }
        this.type = (CollectionType) type;
    }

    @Override
    public String getName() {
        Preconditions.checkState(getKind() != null,
                "The kind of this CollectionLiteralExp is not set.");
        return type.getName() + "(" + parts.toString() + ")";
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        //TODO: This expression could be evaluated statically
        return null;
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.addAll(getParts());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CollectionLiteralExp)) {
            return false;
        }
        final CollectionLiteralExp other = (CollectionLiteralExp) obj;
        if (this.parts != other.parts && (this.parts == null || !this.parts.equals(other.parts))) {
            return false;
        }
        if (this.type != other.type && (this.type == null || !this.type.equals(other.type))) {
            return false;
        }
        CollectionKind myKind = this.getKind();
        CollectionKind otherKind = other.getKind();
        if (myKind != otherKind && (myKind == null || !myKind.equals(otherKind))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.parts != null ? this.parts.hashCode() : 0);
        hash = 73 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        if (type == null) {
            return "<nullCollectionType {" + getParts() + '}';
        }
        return type + "{" + getParts() + '}';
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public CollectionLiteralExp clone() {
        return new CollectionLiteralExp(this);
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
