/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.CollectionLiteralPartVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class CollectionItem extends CollectionLiteralPart {
    private static final long serialVersionUID = 1L;
    
    private OclExpression item;

    public CollectionItem() {
    }

    public CollectionItem(OclExpression item) {
        setItem(item.clone());
    }

    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, 
     * associations and operations are NOT cloned!</b>
     * @param other 
     */
    protected CollectionItem(CollectionItem other) {
        super(other);
        setItem(other.item.clone());
    }
    
    public OclExpression getItem() {
        return item;
    }

    public void setItem(OclExpression item) {
        modifyAttempt();
        changeChild(this.item, item);
        this.item = item;
    }

    @Override
    public Classifier getType() {
        return item.getType();
    }

    @Override
    public String getName() {
        return item.getName();
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.add(item);
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
        final CollectionItem other = (CollectionItem) obj;
        if (this.item != other.item && (this.item == null || !this.item.equals(other.item))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.item != null ? this.item.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        if (item == null) {
            return "<nullCollectionLiteral>";
        }
        return item.toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public CollectionItem clone() {
        return new CollectionItem(this);
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
