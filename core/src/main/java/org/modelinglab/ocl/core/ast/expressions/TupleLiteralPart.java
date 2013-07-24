/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.TypedElement;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.ast.utils.TupleLiteralPartVisitor;
import java.util.List;
import javax.annotation.Nonnull;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class TupleLiteralPart extends TypedElement implements Cloneable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @gortiz OCL Specification says attribute is a Property, but I think a property must be always
     * in a UmlClass, so I changed the type to Variable. This conforms to OCL Concrete Syntax
     * TupleLiteralExpCS production
     */
    private final Variable attribute;

    public TupleLiteralPart(@Nonnull Variable attribute) {
        changeChild(null, attribute);
        this.attribute = attribute;
    }

    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, 
     * associations and operations are NOT cloned!</b>
     */
    protected TupleLiteralPart(TupleLiteralPart other) {
        super(other);
        attribute = other.attribute.clone();
        changeChild(null, attribute);
    }

    public Variable getAttribute() {
        return attribute;
    }

    @Override
    public Classifier getType() {
        return attribute.getType();
    }

    @Override
    @Deprecated
    public void setType(Classifier type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        return attribute.getName();
    }

    @Override
    @Deprecated
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.add(attribute);
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
        final TupleLiteralPart other = (TupleLiteralPart) obj;
        if (this.attribute != other.attribute && (this.attribute == null || !this.attribute.equals(other.attribute))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.attribute != null ? this.attribute.hashCode() : 0);
        return hash;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public TupleLiteralPart clone() {
        return new TupleLiteralPart(this);
    }

    public <Result, Arg> Result accept(TupleLiteralPartVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public String toString() {
        if (getAttribute() == null) {
            return "<nullTupleAtt>";
        }
        else {
            return getAttribute().toString();
        }
    }
}
