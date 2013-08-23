/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import java.util.ArrayList;
import java.util.List;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;

/**
 * A TemplateParameterType is used to refer to generic types in parameterized definitions. It is used in the
 * standard library to represent the parameterized collection operations. A TemplateParameterType is usually
 * named “T” (or “T2,” “T3,” and so on, when more than one type parameter is involved).
 * <p/>
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TemplateParameterType extends Classifier {

    private static final long serialVersionUID = 1L;
    public static final String GENERIC_SOURCE_PARAM_NAME = "SOURCE_TYPE";
    public static final String GENERIC_COLLECTION_PARAM_NAME = "T";
    private static TemplateParameterType genericCollectionElement;
    private static TemplateParameterType genericSourceElement;
    String specification;

    public TemplateParameterType(String specification) {
        this.specification = specification;
    }

    public TemplateParameterType(TemplateParameterType other) {
        super(other);
        specification = other.specification;
    }

    public static TemplateParameterType getGenericCollectionElement() {
        if (genericCollectionElement == null) {
            genericCollectionElement = new TemplateParameterType(GENERIC_COLLECTION_PARAM_NAME);
        }
        return genericCollectionElement;
    }

    public static TemplateParameterType getGenericSourceElement() {
        if (genericSourceElement == null) {
            genericSourceElement = new TemplateParameterType(GENERIC_SOURCE_PARAM_NAME);
        }
        return genericSourceElement;
    }

    @Override
    public ClassifierType getClassifierType() {
        return new ClassifierType(this);
    }

    @Override
    public List<Classifier> getSuperClassifiers() {
        List<Classifier> result = new ArrayList<Classifier>(1);
        result.add(AnyType.getInstance());
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
        final TemplateParameterType other = (TemplateParameterType) obj;
        if ((this.specification == null) ? (other.specification != null) : !this.specification.
                equals(other.specification)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.specification != null ? this.specification.hashCode() : 0);
        return hash;
    }

    @Override
    protected boolean conformsToProtected(Classifier conformsTarget,
                                          TemplateRestrictions instanciatedTemplates) {
        return conformsTarget.conformsWithTemplate(this, instanciatedTemplates);
    }

    @Override
    protected boolean conformsToTemplate(TemplateParameterType conformsTarget,
                                         TemplateRestrictions instanciatedTemplates) {
        return conformsTarget.getSpecification().equals(getSpecification());
    }

    @Override
    public String getName() {
        return getSpecification();
    }

    /**
     *
     * @return An un-interpreted opaque definition of the template parameter type.
     */
    public String getSpecification() {
        return specification;
    }

    @Override
    public TemplateParameterType clone() {
        return this;
    }

    @Override
    public <Result, Arg> Result accept(ClassifierVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
