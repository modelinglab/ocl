/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TupleType;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.IllegalOclExpression;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TupleAttributeCallExp extends CallExp {

    private String tupleAttributeId;

    public TupleAttributeCallExp() {
    }

    public TupleAttributeCallExp(TupleAttributeCallExp other) {
        super(other);
        this.tupleAttributeId = other.tupleAttributeId;
    }
    
    @Override
    public String getName() {
        return tupleAttributeId;
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        return null;
    }

    public String getTupleAttributeId() {
        return tupleAttributeId;
    }

    public void setTupleAttributeId(String tupleAttributeId) {
        this.tupleAttributeId = tupleAttributeId;
    }

    @Override
    public String toString() {
        if (source == null) {
            if (tupleAttributeId == null) {
                return "<nullSource>.<nullTupleAttribute>";
            }
            return "<nullSource>."+tupleAttributeId;
        }
        else {
            if (tupleAttributeId == null) {
                return source.toString() + ".<nullTupleAttribute>";
            }
            return source.toString() + '.' + tupleAttributeId;
        }
    }

    @Override
    public Classifier getType() {
        OclExpression source = getSource();
        if (source == null) {
            throw new AssertionError("this should be checked by #checkIsValid()");
        }
        Classifier sourceType = source.getType();
        if (! (sourceType instanceof TupleType)) {
            throw new AssertionError("this should be checked by #checkIsValid()");
//            throw new IllegalOclExpression(this, "Source of an " + getClass() + " must be instance "
//                    + "of TupleType, but is " +sourceType.getClass()+".");
        }
        TupleType tupleType = (TupleType) sourceType;
        Classifier result = tupleType.getAttributeType(tupleAttributeId);
        if (result == null) {
            throw new AssertionError("this should be checked by #checkIsValid()");
//            throw new IllegalOclExpression(this, "Tuple attribute type with id "
//                    + tupleAttributeId +" is not defined in "+tupleType+".");
        }
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
        final TupleAttributeCallExp other = (TupleAttributeCallExp) obj;
        if ((this.tupleAttributeId == null) ? (other.tupleAttributeId != null) : !this.tupleAttributeId.equals(other.tupleAttributeId)) {
            return false;
        }
        return equalsCallExp(other);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 29 * hash + (this.tupleAttributeId != null ? this.tupleAttributeId.hashCode() : 0);
        return hash;
    }

    @Override
    public OclExpression clone() {
        return new TupleAttributeCallExp(this);
    }

    @Override
    public <Result, Arg> Result accept(OclExpressionsVisitor<Result, Arg> visitor, Arg args) {
        return visitor.visit(this, args);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
    
}
