/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import java.io.Serializable;
import java.util.*;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.utils.Visitable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class Element implements Visitable, Serializable {

    private String name;
    private Namespace namespace;
    private boolean immutable;
    private final Map<Class, OclAnnotation> annotations;
    private Element parent;

    public Element() {
        annotations = new HashMap<Class, OclAnnotation>();
        immutable = false;
    }

    protected Element(Element other) {
        name = other.name;
        immutable = false;
        annotations = new HashMap<Class, OclAnnotation>(other.annotations.size());
        for (Class key : other.annotations.keySet()) {
            annotations.put(key, new OclAnnotation(other.annotations.get(key).getElement()));
        }
    }

    /**
     * {@inheritDoc }
     */
    public Element getTreeParent() {
        return parent;
    }

    /**
     * {@inheritDoc }
     */
    public Object getTreeChildId(@Nonnull Element child) throws IllegalArgumentException {
        List<Element> children = getTreeChildren();
        int index = children.indexOf(child);
        if (index == -1) {
            throw new IllegalArgumentException(child + " is not contained by " +this);
        }
        Element casted = (Element) child;
        return casted.getName() + "." + index;
    }

    /**
     * {@inheritDoc }
     */
    @Nonnull
    public List<Element> getTreeChildren() {
        return new LinkedList<Element>();
    }

    /**
     * Set the parent of this OclExpression. If this.parent is not null, then only this.parent
     * should call this method (to remove itself as parent). This invariant is not checkeable, so
     * you must be careful when call this function!
     *
     * @param parent
     */
    protected final void setTreeParent(Element parent) {
        modifyAttempt();
        if (this.parent != null && parent != null || this.parent == null && parent == null) {
            throw new IllegalArgumentException("A parent can only change from null to nonull or vice"
                    + " versa.");
        }
        this.parent = parent;
    }

    protected void changeChild(Element previousChild, Element newChild) {
        assert previousChild == null || previousChild.getTreeParent() == this :
                "If previous child is not null, only its parent can change its parent.";
        if (previousChild == newChild) { //gortiz: I known I am using == instead of equals!
            return;
        }
        if (newChild != null) {
            newChild.setTreeParent(this);
        }
        if (previousChild != null) {
            previousChild.setTreeParent(null);
        }
    }

    public <E> E getAnnotation(Class<E> key) {
        OclAnnotation annotation = annotations.get(key);
        if (annotation == null) {
            return null;
        }
        return (E) annotation.getElement();
    }

    public <E> E setAnnotation(Class<E> key, E value) {
        modifyAttempt();
        OclAnnotation newValue = new OclAnnotation(value);

        OclAnnotation oldValue = annotations.put(key, newValue);

        if (oldValue == null) {
            return null;
        }
        return (E) oldValue.getElement();
    }
    
    public <E> E setAnnotation(E value) {
        modifyAttempt();
        
        OclAnnotation newValue = new OclAnnotation(value);

        OclAnnotation oldValue = annotations.put(value.getClass(), newValue);

        if (oldValue == null) {
            return null;
        }
        return (E) oldValue.getElement();
    }

    public <E> E removeAnnotation(Class<E> key) {
        modifyAttempt();
        OclAnnotation annotation = annotations.remove(key);
        if (annotation == null) {
            return null;
        }
        return (E) annotation.getElement();
    }

    public Set<Class> getAllAnnotations() {
        return annotations.keySet();
    }

    public final void correctnessCheck() throws IllegalStateException {
    }

    public boolean isCorrect() {
        try {
            correctnessCheck();
            return true;
        } catch (IllegalStateException ex) {
            return false;
        }
    }

    /**
     * {@inheritDoc }
     */
    public String getName() {
        return name;
    }

    final protected String getNameProtected() {
        return name;
    }

    /**
     * {@inheritDoc }
     */
    public void setName(String name) {
        modifyAttempt();
        this.name = name;
    }

    /**
     * Changes the {@link Element#name} attribute. Does not check if the element is immutable or
     * not.
     */
    final protected void setNameProtected(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        return getName();
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespaceUnsecure(Namespace namespace) {
        modifyAttempt();
        this.namespace = namespace;
    }

    public void addOwnedComment(Comment commentToAdd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Comment> getOwnedComments() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean removeOwnedComment(Comment commentToRemove) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setOwnedComments(Collection<? extends Comment> ownedComments) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected boolean elementEquals(Element other) {
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (!this.annotations.equals(other.annotations)) {
            return false;
        }
        return true;
    }
   
    protected final void modifyAttempt() {
        if (immutable) {
            throw new IllegalStateException("This class is inmmutable.");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
//        hash = 97 * hash + (this.namespace != null ? this.namespace.hashCode() : 0);
        return hash;
    }

    @Override
    public abstract boolean equals(Object obj);
    
    public String getPath() {
        StringBuilder sb = new StringBuilder();
        Element e = this;
        Deque<Element> hierarchy = new LinkedList<Element>();
        while (e != null) {
            hierarchy.addLast(e);
            e = e.getTreeParent();
        }
        
        e = hierarchy.removeLast();
        sb.append(e.getName());
        Element parentElement = e;
        while (!hierarchy.isEmpty()) {
            final Element child = hierarchy.removeLast();
            sb.append('/');
            sb.append(parentElement.getTreeChildId(child));
            parentElement = child;
        }
        
        return sb.toString();
    }
}
