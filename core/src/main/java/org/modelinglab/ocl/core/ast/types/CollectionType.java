/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CollectionType extends DataType {

    private static final long serialVersionUID = 1L;
    Classifier elementType;
    private static CollectionType genericInstance = null;

    public CollectionType() {
    }

    public CollectionType(Classifier elementType) {
        Preconditions.checkArgument(elementType != null, "Element type of collection types must be "
                + "different than (java) null.");
        this.elementType = elementType;
    }

    protected CollectionType(CollectionType other) {
        super(other);
        elementType = other.elementType.clone();
    }

    public static CollectionType getGenericInstance() {
        if (genericInstance == null) {
            genericInstance = new CollectionType();
            genericInstance.setElementType(TemplateParameterType.getGenericCollectionElement());
        }
        return genericInstance;
    }

    public Classifier getElementType() {
        return elementType;
    }

    public void setElementType(Classifier elementType) {
        this.elementType = elementType;
    }

    public boolean isOrdered() {
        switch (getCollectionKind()) {
            case BAG:
            case SET:
                return false;
            case ORDERED_SET:
            case SEQUENCE:
                return true;
            default:
                throw new IllegalStateException("This function has no sense in abstract collections");
        }
    }

    public boolean isUnique() {
        switch (getCollectionKind()) {
            case BAG:
                return false;
            case SEQUENCE:
            case ORDERED_SET:
            case SET:
                return true;
            default:
                throw new IllegalStateException("This function has no sense in abstract collections");
        }
    }

    public static CollectionType newCollection(CollectionKind kind) {
        CollectionType result;
        switch (kind) {
            case BAG:
                result = new BagType();
                break;
            case COLLECTION:
                result = new CollectionType();
                break;
            case ORDERED_SET:
                result = new OrderedSetType();
                break;
            case SEQUENCE:
                result = new SequenceType();
                break;
            case SET:
                result = new SetType();
                break;
            default:
                throw new AssertionError();
        }
        return result;
    }

    @Override
    public void fixTemplates(TemplateRestrictions restrictions) {
        super.fixTemplates(restrictions);
        restrictions.instantiate("T", elementType, true);
    }

    @Override
    public List<Classifier> getSuperClassifiers() {
        assert getElementType() != null;

        List<Classifier> elementSuperClassifiers;

        elementSuperClassifiers = getElementType().getSuperClassifiers();

        List<Classifier> result;
        CollectionType ct;

        if (getCollectionKind() != CollectionKind.COLLECTION) {
            /*
             * Result will have a collection foreach element in elementSuperClassifiers, a specific
             * collection type foreach element in elementSuperClassifiers, the AnyType, and a collection
             * and a specific collection type of elementType
             */
            result = new ArrayList<Classifier>(elementSuperClassifiers.size() * 2 + 1 + 2);

            CollectionKind collectionKind = getCollectionKind();
            ct = CollectionType.newCollection(collectionKind);
            ct.setElementType(getElementType());
            result.add(ct);

            for (Classifier superElement : elementSuperClassifiers) {
                ct = CollectionType.newCollection(collectionKind);
                ct.setElementType(superElement);
                result.add(ct);
            }

            ct = new CollectionType();
            ct.setElementType(getElementType());
            result.add(ct);
        } else {
            result = new ArrayList<Classifier>(elementSuperClassifiers.size() + 1);
        }
        for (Classifier superElement : elementSuperClassifiers) {
            ct = new CollectionType();
            ct.setElementType(superElement);
            result.add(ct);
        }

        result.add(AnyType.getInstance());

        return result;
    }

    @Override
    public CollectionType getRestrictedType(TemplateRestrictions restrictions) {
        if (getElementType() instanceof TemplateParameterType) {
            TemplateParameterType template = (TemplateParameterType) getElementType();
            Classifier newElement = restrictions.getInstance(template.getSpecification());
            if (newElement != null) {
                CollectionType result = CollectionType.newCollection(getCollectionKind());
                result.setElementType(newElement);
                return result;
            } else { //restrictions does not contains elementType
                return this;
            }
        } else { //elementType is not a tempate
            return this;
        }
    }

    @Override
    protected void conformsPreconditions(Classifier otherClassifier) {
        super.conformsPreconditions(otherClassifier);
        Preconditions.checkState(elementType != null,
                "To evaluate conforms with CollectionType#getElementType() must not be null");
    }

    @Override
    protected boolean conformsToProtected(Classifier conformsTarget, TemplateRestrictions instanciatedTemplates) {
        if (!(conformsTarget instanceof CollectionType)) {
            return false;
        }
        final CollectionType otherCollection = (CollectionType) conformsTarget;
        if (!elementType.conformsTo(otherCollection.getElementType(), instanciatedTemplates)) {
            return false;
        }
        return otherCollection.getCollectionKind() == this.getCollectionKind()
                || otherCollection.getCollectionKind() == CollectionKind.COLLECTION;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CollectionType other = (CollectionType) obj;
        if (this.elementType != other.elementType && (this.elementType == null || !this.elementType.equals(other.elementType))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.elementType != null ? this.elementType.hashCode() : 0);
        return hash;
    }

    @Override
    public CollectionType clone() {
        return new CollectionType(this);
    }

    @Override
    public String getName() {
        return getCollectionKind().toString() + "(" + elementType + ')';
    }

    @Override
    public ClassifierType getClassifierType() {
        return new ClassifierType(this);
    }

    public CollectionKind getCollectionKind() {
        return CollectionKind.COLLECTION;
    }

    @Override
    public <Result, Arg> Result accept(ClassifierVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    public static enum CollectionKind {

        COLLECTION, BAG, SET, ORDERED_SET, SEQUENCE;

        @Override
        public String toString() {
            switch (this) {
                case COLLECTION:
                    return "Collection";
                case BAG:
                    return "Bag";
                case SET:
                    return "Set";
                case ORDERED_SET:
                    return "OrderedSet";
                case SEQUENCE:
                    return "Sequence";
                default:
                    throw new AssertionError();
            }
        }
    }
}
