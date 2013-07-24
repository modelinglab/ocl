/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import com.google.common.base.Preconditions;
import java.util.Collection;
import org.modelinglab.ocl.core.ast.AssociationEnd;
import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.ast.UmlObject.NotInitializedProperty;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.IllegalOclExpression;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class AssociationEndCallExp extends NavigationCallExp {
    private static final long serialVersionUID = 1L;
    
    private AssociationEnd referredAssociationEnd;

    public AssociationEndCallExp() {
    }

    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, 
     * associations and operations are NOT cloned!</b>
     */
    protected AssociationEndCallExp(AssociationEndCallExp other) {
        super(other);
        referredAssociationEnd = other.getReferredAssociationEnd();
    }

    public AssociationEnd getReferredAssociationEnd() {
        return referredAssociationEnd;
    }

    public void setReferredAssociationEnd(AssociationEnd referredAssociationEnd) {
        modifyAttempt();
        this.referredAssociationEnd = referredAssociationEnd;
    }

    @Override
    public Classifier getType() {
        Preconditions.checkState(getReferredAssociationEnd() != null,
                "The referredAssociationEnd of this AssociationEndCalLExp is not set.");
        return getReferredAssociationEnd().getType();
    }

    @Override
    public String getName() {
        Preconditions.checkState(getReferredAssociationEnd() != null,
                "The referredAssociationEnd of this AssociationEndCalLExp is not set.");
        return getReferredAssociationEnd().getName();
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        OclValue v = getSource().getStaticEvaluation();
        if (v == null) {
            return null;
        }
        assert v instanceof UmlObject : "this should be checked by #checkIsValid()";
        try {
            return ((UmlObject) v).getProperty(getReferredAssociationEnd().getName());
        } catch (IllegalArgumentException ex) {
            throw new AssertionError("this should be checked by #checkIsValid()");
        } catch (NotInitializedProperty ex) {
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
        final AssociationEndCallExp other = (AssociationEndCallExp) obj;
        if (this.referredAssociationEnd != other.referredAssociationEnd && (this.referredAssociationEnd == null || !this.referredAssociationEnd.equals(other.referredAssociationEnd))) {
            return false;
        }
        return equalsNavigationCall(other);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.referredAssociationEnd != null ? this.referredAssociationEnd.hashCode() : 0);
        return 61 * hash * super.hashCode();
    }

    @Override
    public String toString() {
        if (source == null) {
            if (referredAssociationEnd == null) {
                return "<nullSource>."+referredAssociationEnd.toString();
            }
            return "<nullSource>.<nullAssociationEnd>";
        }
        else {
            if (referredAssociationEnd == null) {
                return source.toString() + ".<nullAssociationEnd>";
            }
            return source.toString() + '.' + referredAssociationEnd.toString();
        }
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclExpressionsVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public AssociationEndCallExp clone() {
        return new AssociationEndCallExp(this);
    }
}
