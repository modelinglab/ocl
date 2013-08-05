/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class OrderedSet<E> extends ArrayList<E> {
    private static final long serialVersionUID = 1L;

    public OrderedSet(Collection<? extends E> c) {
        super(c.size());
        addAll(c);
    }

    public OrderedSet() {
    }

    public OrderedSet(int initialCapacity) {
        super(initialCapacity);
    }
    
    @Override
    public OrderedSet<E> clone() {
        return new OrderedSet<E>(this);
    }

    @Override
    public boolean add(E e) {
        if (!contains(e)) {
            return super.add(e);
        }
        return false;
    }

    @Override
    public void add(int index, E element) {
        if (!contains(element)) {
            super.add(index, element);
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E e : c) {
            result = add(e) || result;
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported.");
    }
    
}
