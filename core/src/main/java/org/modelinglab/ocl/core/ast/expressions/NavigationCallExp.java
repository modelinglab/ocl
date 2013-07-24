/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.AssociationEnd;
import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.utils.OrderedSet;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class NavigationCallExp extends FeatureCallExp {
    
    private AssociationEnd navigationSource;
    private OrderedSet<OclExpression> qualifiers = new OrderedSet<OclExpression>();

    public NavigationCallExp() {
    }
    
    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, 
     * associations and operations are NOT cloned!</b>
     */
    protected NavigationCallExp(NavigationCallExp other) {
        super(other);
        navigationSource = other.getNavigationSource();
        qualifiers = new OrderedSet<OclExpression>(other.getQualifiers().size());
        for (OclExpression oclExpression : other.getQualifiers()) {
            qualifiers.add(oclExpression.clone());
        }
    }
   
    public AssociationEnd getNavigationSource() {
        return navigationSource;
    }

    public void setNavigationSource(AssociationEnd navigationSource) {
        modifyAttempt();
        this.navigationSource = navigationSource;
    }

    public OrderedSet<OclExpression> getQualifiers() {
        return qualifiers;
    }

    public void addQualifier(OclExpression qualifierToAdd) {
        modifyAttempt();
        getQualifiers().add(qualifierToAdd);
    }

    public boolean removeQualifier(OclExpression qualifierToRemove) {
        modifyAttempt();
        return getQualifiers().remove(qualifierToRemove);
    }

    public void setQualifiers(Collection<? extends OclExpression> qualifiers) {
        modifyAttempt();
        this.qualifiers = new OrderedSet<OclExpression>(qualifiers);
    }

    public boolean replaceQualifier(OclExpression oldQualifier, OclExpression newQualifier) {
        if (!getQualifiers().contains(oldQualifier)) {
            return false;
        }
        qualifiers.set(qualifiers.indexOf(oldQualifier), newQualifier);
        return true;
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.addAll(qualifiers);
        return result;
    }

    protected boolean equalsNavigationCall(@Nonnull NavigationCallExp other) {
        if (this.navigationSource != other.navigationSource && (this.navigationSource == null || !this.navigationSource.equals(other.navigationSource))) {
            return false;
        }
        if (this.qualifiers != other.qualifiers && (this.qualifiers == null || !this.qualifiers.equals(other.qualifiers))) {
            return false;
        }
        return equalsFeatureCall(other);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.navigationSource != null ? this.navigationSource.hashCode() : 0);
        hash = 17 * hash + (this.qualifiers != null ? this.qualifiers.hashCode() : 0);
        return hash;
    }

    
}
