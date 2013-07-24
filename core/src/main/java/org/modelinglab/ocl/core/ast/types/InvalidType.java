/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * 
 * InvalidType represents a type that conforms to all types except the VoidType type. The only 
 * instance of InvalidType is Invalid, which is further defined in the standard library. 
 * Furthermore Invalid has exactly one runtime instance identified as OclInvalid.
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public final class InvalidType extends Classifier {

    private static final long serialVersionUID = 1L;
    private static InvalidType instance;
    private final ClassifierType classifierType = new ClassifierType(this);

    private InvalidType() {
    }

    public static InvalidType getInstance() {
        if (instance == null) {
            instance = new InvalidType();
        }
        return instance;
    }

    @Override
    public ClassifierType getClassifierType() {
        return classifierType;
    }

    @Override
    protected boolean conformsToProtected(Classifier conformsTarget, TemplateRestrictions instanciatedTemplates) {
        return true;
    }

    @Override
    public String getName() {
        return "InvalidType";
    }

    @Override
    public List<Classifier> getSuperClassifiers() {
        List<Classifier> result = new ArrayList<Classifier>(1);
        result.add(AnyType.getInstance());
        return result;
    }

    @Override
    public boolean oclIsInvalid() {
        return true;
    }

    @Override
    public boolean oclIsUndefined() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof InvalidType;
    }

    @Override
    public int hashCode() {
        return 79;
    }

    @Override
    public InvalidType clone() {
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
