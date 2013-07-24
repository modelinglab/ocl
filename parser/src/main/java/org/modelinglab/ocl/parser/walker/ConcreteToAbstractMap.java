/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.parser.sablecc.node.Node;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ConcreteToAbstractMap implements Map<Node, Object>, Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private final HashMap<Node, Object> map = new HashMap<Node, Object>();

    @Override
    public void clear() {
        assert map.isEmpty();
        map.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        Preconditions.checkArgument(key != null);
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        Preconditions.checkArgument(value != null);
        return map.containsValue(value);
    }

    @Override
    public Set<Entry<Node, Object>> entrySet() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Object get(Object key) {
        Preconditions.checkArgument(key != null);
        return map.get(key);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Set<Node> keySet() {
        return map.keySet();
    }

    @Override
    public Object put(Node key, Object value) {
        Preconditions.checkArgument(key != null);
        Preconditions.checkArgument(value != null);
        Preconditions.checkArgument(value != this);
        return map.put(key, value);
    }

    @Override
    public void putAll(Map<? extends Node, ? extends Object> m) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Object remove(Object key) {
        Preconditions.checkArgument(key != null);
        assert map.get((Node) key) != null;
        return map.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }
    
    @Override
    public ConcreteToAbstractMap clone() {
        ConcreteToAbstractMap clone = new ConcreteToAbstractMap();
        clone.map.putAll(this.map);
        return clone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConcreteToAbstractMap other = (ConcreteToAbstractMap) obj;
        if (this.map != other.map && (this.map == null || !this.map.equals(other.map))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.map != null ? this.map.hashCode() : 0);
        return hash;
    }
    
    
}
