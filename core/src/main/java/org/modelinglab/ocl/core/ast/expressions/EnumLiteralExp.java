/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import java.util.Collection;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class EnumLiteralExp extends LiteralExp {
    private static final long serialVersionUID = 1L;
    
    UmlEnumLiteral enumerationLiteral;

    public EnumLiteralExp() {
    }

    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, 
     * associations and operations are NOT cloned!</b>
     */
    public EnumLiteralExp(EnumLiteralExp other) {
        super(other);
        enumerationLiteral = other.enumerationLiteral;
    }

    public UmlEnumLiteral getEnumerationLiteral() {
        return enumerationLiteral;
    }

    public void setEnumerationLiteral(UmlEnumLiteral enumerationLiteral) {
        modifyAttempt();
        this.enumerationLiteral = enumerationLiteral;
    }

    @Override
    public Classifier getType() {
        Preconditions.checkState(getEnumerationLiteral() != null,
                "The enumerationLiteral of this EnumerationLiteralExp is not set.");
        return getEnumerationLiteral().getType();
    }

    @Override
    public String getName() {
        Preconditions.checkState(getEnumerationLiteral() != null,
                "The enumerationLiteral of this EnumerationLiteralExp is not set.");
        return getEnumerationLiteral().getName();
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        //TODO: This expression could be evaluated statically
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EnumLiteralExp other = (EnumLiteralExp) obj;
        if (this.enumerationLiteral != other.enumerationLiteral && (this.enumerationLiteral == null || !this.enumerationLiteral.equals(other.enumerationLiteral))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.enumerationLiteral != null ? this.enumerationLiteral.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        if (enumerationLiteral == null) {
            return "<nullEnumLiteral>";
        }
        return enumerationLiteral.toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public EnumLiteralExp clone() {
        return new EnumLiteralExp(this);
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
