/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import java.util.Collections;
import java.util.List;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;

/**
 * {@link AnyType} is the metaclass of the special type OclAny, which is the type to which all other
 * types conform. OclAny is the sole instance of AnyType. This metaclass allows defining the special
 * property of being the generalization of all other Classifiers, including Classes, DataTypes, and 
 * PrimitiveTypes.
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@javax.annotation.concurrent.Immutable
public final class AnyType extends Classifier {
    
    private static AnyType instance;
    private static final long serialVersionUID = 1L;
    
    private final ClassifierType classifierType = new ClassifierType(this);
    
    private AnyType() {}
    
    public static AnyType getInstance() {
        if (instance == null) {
            instance = new AnyType();
        }
        return instance;
    }

    @Override
    public ClassifierType getClassifierType() {
        return classifierType;
    }

    @Override
    public <Result, Arg> Result accept(ClassifierVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    protected boolean conformsToProtected(Classifier conformsTarget, TemplateRestrictions instanciatedTemplates) {
        return false;
    }

    @Override
    public String getName() {
        return "AnyType";
    }

    @Override
    public List<Classifier> getSuperClassifiers() {
        return Collections.emptyList();
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof AnyType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public AnyType clone() {
        return this;
    }
}
