/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.values.ClassifierValue;
import java.util.Collection;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TypeExp extends OclExpression {
    private static final long serialVersionUID = 1L;
    
    private final Classifier value;
    private final ClassifierType expressionType;

    public TypeExp(ClassifierType type) {
        Preconditions.checkArgument(type != null, "An expression type must be referred to a not null classifier.");
        this.expressionType = type;
        this.value = type.getReferredClassifier();
    }

    public TypeExp(Classifier referredType) {
        Preconditions.checkArgument(referredType != null, "An expression type needs a not null classifier as value.");
        this.value = referredType;
        this.expressionType = referredType.getClassifierType();
    }

    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, 
     * associations and operations are NOT cloned!</b>
     */
    protected TypeExp(TypeExp other) {
        super(other);
        value           = other.value;
        expressionType  = other.expressionType;
    }

    @Override
    public String getName() {
        assert value != null;
        return value.getName();
    }

    @Override
    public ClassifierType getType() {
        return expressionType;
    }

    @Override
    @Deprecated
    public void setType(Classifier type) {
        throw new UnsupportedOperationException("TypeExp#type(Classifier) is not seteable.");
    }

    @Override
    public ClassifierValue getStaticEvaluation() {
        return new ClassifierValue(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TypeExp other = (TypeExp) obj;
        if (this.value != other.value && (this.value == null || !this.value.equals(other.value))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public TypeExp clone() {
        return new TypeExp(this);
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
