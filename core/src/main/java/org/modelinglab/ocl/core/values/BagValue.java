/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.utils.ValueVisitor;

/**
 *
 * @param <E> 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class BagValue<E extends OclValue<?>> extends OclValue<List<E>> {
    private static final long serialVersionUID = 1L;

    private final BagType type;
    public BagValue(Collection<E> value, Classifier elementType) {
        super(new ArrayList<>(value));
        this.type = new BagType(elementType);
    }
    
    public BagValue(List<E> value, Classifier elementType, boolean isInmutable) {
        super(value);
        this.type = new BagType(elementType);
    }
    
    public BagValue(SetValue<E> set) {
        super(set.getValue());
        this.type = new BagType(set.getType().getElementType());
    }
    
    public BagValue(SequenceValue<E> seq) {
        super(seq.getValue());
        this.type = new BagType(seq.getType().getElementType());
    }
    
    public BagValue(OrderedSetValue<E> orderedSet) {
        super(orderedSet.getValue());
        this.type = new BagType(orderedSet.getType().getElementType());
    }
    
    @Override
    public <Result, Arg> Result accept(ValueVisitor<Result, Arg> visitor, Arg arg) {
        return visitor.visit(this, arg);
    }

    @Override
    public BagType getType() {
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
        return "Bag{" + sb.toString() + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        final BagValue<?> other = (BagValue<?>) obj;
        
        if (this.type != other.type && (this.type == null || !this.type.equals(other.type))) {
            return false;
        }
        
        if (this.getValue().size() != other.getValue().size()) {
            return false;
        }
        Multiset<E> myMultset = HashMultiset.create(this.getValue());
        Multiset<?> otherMultset = HashMultiset.create(other.getValue());
        
        return myMultset.equals(otherMultset);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
}
