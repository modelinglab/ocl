/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.values.utils.ValueVisitor;

/**
 *
 * @param <E> 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class SequenceValue<E extends OclValue<?>> extends OclValue<List<E>> {
    private static final long serialVersionUID = 1L;

    private final SequenceType type;
    public SequenceValue(Collection<E> value, Classifier elementType) {
        super(new ArrayList<>(value));
        this.type = new SequenceType(elementType);
    }
    
    public SequenceValue(List<E> value, Classifier elementType, boolean isInmutable) {
        super(value);
        this.type = new SequenceType(elementType);
    }
    
    public SequenceValue(BagValue<E> bag) {
        super(bag.getValue());
        this.type = new SequenceType(bag.getType().getElementType());
    }
    
    public SequenceValue(SetValue<E> set) {
        super(set.getValue());
        this.type = new SequenceType(set.getType().getElementType());
    }
    
    public SequenceValue(OrderedSetValue<E> orderedSet) {
        super(orderedSet.getValue());
        this.type = new SequenceType(orderedSet.getType().getElementType());
    }

    @Override
    public <Result, Arg> Result accept(ValueVisitor<Result, Arg> visitor, Arg arg) {
        return visitor.visit(this, arg);
    }

    @Override
    public SequenceType getType() {
        return type;
    }

    @Override
    public List<E> getValue() {
        return Collections.unmodifiableList(super.getValue());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(5 * getValue().size());
        for (final E e : getValue()) {
            sb.append(e).append(", ");
        }
        if (!getValue().isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return "Sequence{" + sb.toString() + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SequenceValue<?> other = (SequenceValue<?>) obj;
        if (this.type != other.type && (this.type == null || !this.type.equals(other.type))) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
}
