/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class AttributeCallExp extends FeatureCallExp {
    private static final long serialVersionUID = 1L;
    
    private Attribute referredAttribute;

    public AttributeCallExp() {
    }

    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, 
     * associations and operations are NOT cloned!</b>
     */
    protected AttributeCallExp(AttributeCallExp other) {
        super(other);
        referredAttribute = other.getReferredAttribute();
    }

    public Attribute getReferredAttribute() {
        return referredAttribute;
    }

    public void setReferredAttribute(Attribute referredAttribute) {
        modifyAttempt();
        this.referredAttribute = referredAttribute;
    }

    @Override
    public Classifier getType() {
        Preconditions.checkState(getReferredAttribute() != null,
                "The referredAttribute of this AttributeCallExp is not set.");
        return getReferredAttribute().getType();
    }

    @Override
    public String getName() {
        Preconditions.checkState(getReferredAttribute() != null,
                "The referredAttribute of this AttributeCallExp is not set.");
        return getReferredAttribute().getName();
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        OclValue v = getSource().getStaticEvaluation();
        if (v == null) {
            return null;
        }
        assert v instanceof UmlObject : "this should be checked by #checkIsValid()";
        
        try {
            return ((UmlObject) v).getProperty(getReferredAttribute().getName());
        } catch (IllegalArgumentException ex) {
            throw new AssertionError("this should be checked by #checkIsValid()");
        } catch (UmlObject.NotInitializedProperty ex) {
            throw new OclEvaluationException(this, ex);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AttributeCallExp other = (AttributeCallExp) obj;
        if (this.referredAttribute != other.referredAttribute && (this.referredAttribute == null || !this.referredAttribute.equals(other.referredAttribute))) {
            return false;
        }
        return equalsFeatureCall(other);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.referredAttribute != null ? this.referredAttribute.hashCode() : 0);
        return 97 * hash + super.hashCode();
    }

    @Override
    public String toString() {
        if (source == null) {
            if (referredAttribute == null) {
                return "<nullSource>."+referredAttribute.toString();
            }
            return "<nullSource>.<nullAttribute>";
        }
        else {
            if (referredAttribute == null) {
                return source.toString() + ".<nullAttribute>";
            }
            return source.toString() + '.' + referredAttribute.toString();
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public AttributeCallExp clone() {
        return new AttributeCallExp(this);
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
