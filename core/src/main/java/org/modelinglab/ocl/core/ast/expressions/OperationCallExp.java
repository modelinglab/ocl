/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.standard.operations.bag.IsEqual;
import org.modelinglab.ocl.core.standard.operations.bool.And;
import org.modelinglab.ocl.core.standard.operations.bool.Implies;
import org.modelinglab.ocl.core.standard.operations.bool.Not;
import org.modelinglab.ocl.core.standard.operations.bool.Or;
import org.modelinglab.ocl.core.standard.operations.bool.Xor;
import org.modelinglab.ocl.core.standard.operations.collection.IsDifferent;
import org.modelinglab.ocl.core.standard.operations.integer.Negative;
import org.modelinglab.ocl.core.standard.operations.real.Greater;
import org.modelinglab.ocl.core.standard.operations.real.GreaterOrEqual;
import org.modelinglab.ocl.core.standard.operations.real.Less;
import org.modelinglab.ocl.core.standard.operations.real.LessOrEqual;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class OperationCallExp extends FeatureCallExp {

    private static final long serialVersionUID = 1L;
    private ArrayList<OclExpression> arguments;
    private Operation referredOperation;

    public OperationCallExp() {
        arguments = new ArrayList<OclExpression>();
    }

    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes,
     * attributes, associations and operations are NOT cloned!</b>
     */
    protected OperationCallExp(OperationCallExp other) {
        super(other);
        referredOperation = other.referredOperation;

        arguments = new ArrayList<OclExpression>(other.arguments.size());
        for (OclExpression otherArg : other.arguments) {
            addArgument(otherArg.clone());
        }
    }

    public List<OclExpression> getArguments() {
        return (List<OclExpression>) arguments.clone();
    }

    public void addArgument(OclExpression expToAdd) {
        modifyAttempt();
        changeChild(null, expToAdd);
        arguments.add(expToAdd);
    }

    public boolean removeArgument(OclExpression expToRemove) {
        modifyAttempt();
        if (arguments.remove(expToRemove)) {
            changeChild(expToRemove, null);
            return true;
        }
        return false;
    }

    public void setArguments(Collection<? extends OclExpression> arguments) {
        modifyAttempt();
        for (OclExpression arg : arguments) {
            if (arg.getTreeParent() != null) {
                throw new IllegalArgumentException("A argument to add should have no parent!");
            }
        }
        for (OclExpression arg : this.arguments) {
            changeChild(arg, null);
        }
        for (OclExpression arg : arguments) {
            changeChild(null, arg);
        }
        this.arguments = new ArrayList<OclExpression>(arguments);
    }

    public boolean replaceArgument(OclExpression oldArgument, OclExpression newArgument) {
        if (!getArguments().contains(oldArgument)) {
            return false;
        }
        arguments.set(arguments.indexOf(oldArgument), newArgument);
        return true;
    }

    @Override
    public String getName() {
        Preconditions.checkState(getReferredOperation() != null,
                "The referredOperation of this OperationCallExp is not set.");
        return getReferredOperation().getSignature();
    }

    /**
     * Name of OperationCallExp can not be set
     *
     * @param name
     */
    @Deprecated
    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Classifier getType() {
        Preconditions.checkState(getReferredOperation() != null,
                "The referredOperation of this OperationCallExp is not set.");
        return getReferredOperation().getType();
    }

    public Operation getReferredOperation() {
        return referredOperation;
    }

    public void setReferredOperation(Operation referredOperation) {
        modifyAttempt();
        this.referredOperation = referredOperation;
    }

    @Override
    public OclValue getStaticEvaluation() throws OclEvaluationException {
        OclValue sourceValue = getSource().getStaticEvaluation();
        List<OclValue> argVals = new ArrayList<OclValue>(arguments.size());
        for (OclExpression arg : getArguments()) {
            argVals.add(arg.getStaticEvaluation());
        }
        return getReferredOperation().getStaticEvaluation(getSource().getType(), sourceValue, argVals);
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.addAll(arguments);
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
        final OperationCallExp other = (OperationCallExp) obj;
        if (this.arguments != other.arguments && (this.arguments == null || !this.arguments.equals(other.arguments))) {
            return false;
        }
        if (this.referredOperation != other.referredOperation && (this.referredOperation == null || !this.referredOperation.equals(other.referredOperation))) {
            return false;
        }
        return equalsFeatureCall(other);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.arguments != null ? this.arguments.hashCode() : 0);
        hash = 67 * hash + (this.referredOperation != null ? this.referredOperation.hashCode() : 0);
        return 67 * hash + super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (referredOperation instanceof Not 
                || referredOperation instanceof Negative 
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.real.Negative) {
            sb.append(referredOperation.getName()).append('(');

            if (getSource() == null) {
                sb.append("<nullSource>");
            } else {
                sb.append(source.toString());
            }
            sb.append(")");
            return sb.toString();
        }
        if (referredOperation instanceof And 
                || referredOperation instanceof Or                
                || referredOperation instanceof Implies
                || referredOperation instanceof Xor
                || referredOperation instanceof IsEqual
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.collection.IsEqual
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.oclAny.IsEqual
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.oclVoid.IsEqual
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.sequence.IsEqual
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.sequence.IsEqual
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.set.IsEqual
                || referredOperation instanceof IsDifferent
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.oclAny.IsDifferent
                || referredOperation instanceof Less
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.string.Less
                || referredOperation instanceof Greater
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.string.Greater
                || referredOperation instanceof LessOrEqual
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.string.LessOrEqual
                || referredOperation instanceof GreaterOrEqual
                || referredOperation instanceof org.modelinglab.ocl.core.standard.operations.string.GreaterOrEqual) {
            if (getSource() == null) {
                sb.append("<nullSource>");
            } else {
                sb.append(source.toString());
            }
            sb.append(' ');
            if (getReferredOperation() == null) {
                sb.append(".<nullOperation>");
            } else {
                sb.append(getReferredOperation().getName());
            }
            sb.append(' ');
            if (getArguments().isEmpty()) {
                sb.append("<nullArg>");
            }
            else {
                sb.append(getArguments().get(0));
            }
            return sb.toString();
        }
        else {

            if (getSource() == null) {
                sb.append("<nullSource>");
            } else {
                sb.append(source.toString());
            }
            if (getReferredOperation() == null) {
                sb.append(".<nullOperation>");
            } else {
                if (getSource() != null && getSource().getType() instanceof CollectionType) {
                    sb.append("->");
                } else {
                    sb.append(".");
                }
                sb.append(getReferredOperation().getName());
            }
            sb.append('(').append(Joiner.on(", ").join(getArguments())).append(')');
            return sb.toString();
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public OperationCallExp clone() {
        return new OperationCallExp(this);
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
