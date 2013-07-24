/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import com.google.common.base.Preconditions;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class Classifier extends Element implements Cloneable {
    
    protected static final Logger logger = Logger.getLogger(Classifier.class.getName());
    
    public abstract ClassifierType getClassifierType();
    
    /**
     * Warning: UmlClasses are not cloned!
     * @return 
     */
    @Override
    public abstract Classifier clone();
    
    public abstract <Result, Arg> Result accept(ClassifierVisitor<Result, Arg> visitor, Arg arguments);

    public Classifier() {
    }

    public Classifier(Element other) {
        super(other);
    }
     
    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public String toString() {
        return getName();
    }
    
    public abstract List<Classifier> getSuperClassifiers();
    
    public void fixTemplates(TemplateRestrictions restrictions) {
        restrictions.instantiate(TemplateParameterType.getGenericSourceElement().getSpecification(), this);
    }
    
    public boolean oclIsUndefined() {
        return false;
    }
    
    public boolean oclIsInvalid() {
        return false;
    }
    
    public boolean isCollection() {
        return this instanceof CollectionType;
    }
    
    protected void conformsPreconditions(Classifier otherClassifier) {
        Preconditions.checkNotNull(otherClassifier, 
                "To evaluate conforms with, other classifier must be not null");
    }
    
    protected boolean conformsToTemplate(@Nonnull TemplateParameterType conformsTarget, TemplateRestrictions templates) {
        String template = conformsTarget.getSpecification();
        Classifier instance = templates.getInstance(template);
        if (instance != null) {
            if (conformsTo(instance, templates)) { //this is "instanceof" instance
                return true;
            }
            if (!templates.isFixed(template)) { //this template is not fixed, maybe it can be relaxed to a supertype
                if(instance.conformsTo(this, templates)) {
                    templates.instantiate(template, this, false);
                    logger.log(Level.FINEST, "%s is relaxed from %s to %s", new Object[]{template, instance.toString(), this.toString()});
                    return true;
                }
            }
            return false;
        }
        else {
            templates.instantiate(template, this, false);
            logger.log(Level.FINEST, "%s is instanciated as %s", new Object[] {template, this});
            return true;
        }
    }
    
    protected boolean conformsWithTemplate(@Nonnull TemplateParameterType conformsTarget, TemplateRestrictions templates) {
        String template = conformsTarget.getSpecification();
        Classifier instance = templates.getInstance(template);
        if (instance != null) {
            if (conformsWith(instance, templates)) { //instance is "instanceof" this
                return true;
            }
            if (!templates.isFixed(template)) { //this template is not fixed, maybe it can be relaxed to a supertype
                if(this.conformsWith(instance, templates)) {
                    templates.instantiate(template, this, false);
                    logger.log(Level.FINEST, "%s is relaxed from %s to %s", new Object[]{template, instance.toString(), this});
                    return true;
                }
            }
            return false;
        }
        else {
            templates.instantiate(template, this, false);
            logger.log(Level.FINEST, "%s is instanciated as %s", new Object[] {template, this});
            return true;
        }
    }
    
    protected abstract boolean conformsToProtected(@Nonnull Classifier conformsTarget, TemplateRestrictions templates);

    public final boolean conformsTo(@Nonnull Classifier conformsTarget) {
        return conformsTo(conformsTarget, new TemplateRestrictions());
    }
    
    public final boolean conformsTo(@Nonnull Classifier conformsTarget, TemplateRestrictions templates) {
        conformsPreconditions(conformsTarget);
        if (conformsTarget instanceof AnyType) {
            return true;
        }
        if (conformsTarget instanceof TemplateParameterType) {
            return conformsToTemplate((TemplateParameterType) conformsTarget, templates);
        }
        return conformsToProtected(conformsTarget, templates);
    }

    public boolean conformsWith(@Nonnull Classifier classifier, TemplateRestrictions templates) {
        conformsPreconditions(classifier);
        return classifier.conformsTo(this, templates);
    }
    
    public Classifier getRestrictedType(TemplateRestrictions restrictions) {
        return this;
    }

    public boolean classifierEquals(@Nonnull Classifier c) {
        return elementEquals(c);
    }
    
    
}
